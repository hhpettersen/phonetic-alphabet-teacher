package com.app.phoneticalphabet.ui.postquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.phoneticalphabet.databinding.PostQuizFragmentBinding
import com.app.phoneticalphabet.helpers.Status
import com.app.phoneticalphabet.models.HighScore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostQuizFragment : Fragment() {

    private var _binding: PostQuizFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: PostQuizFragmentArgs by navArgs()

    private val viewModel: PostQuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PostQuizFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val score = args.score

        viewModel.res.observe(viewLifecycleOwner, { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data.let { highScore ->
                        val savedScore = highScore?.score ?: 0
                        ui(score, savedScore)
                        updateHighScore(score, savedScore, highScore)
                    }
                }
                Status.LOADING -> Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                Status.ERROR -> Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        })

        binding.scoreText.text = "Your score: $score"

        binding.playAgainButton.setOnClickListener {
            findNavController().navigate(PostQuizFragmentDirections.actionPostQuizFragmentToQuizFragment())
        }

        binding.navigateHomeButton.setOnClickListener {
            findNavController().navigate(PostQuizFragmentDirections.actionPostQuizFragmentToDashBoardFragment())
        }
    }

    private fun updateHighScore(score: Int, savedScore: Int, highScore: HighScore?) {
        if (score > savedScore) {
            highScore?.score = score
            viewModel.insertHighScore(highScore)
        }
    }

    private fun ui (score: Int, savedScore: Int) {
        if (score > savedScore) {
            binding.scoreInfoText.text = "You just set a new highscore, congratulations!"
//            binding.lottieAnimationView.setAnimation(R.raw.fireworks)
        } else {
            binding.scoreInfoText.text = "Keep trying to beat your highscore!"
//            binding.lottieAnimationView.setAnimation(R.raw.dog_run)
        }
    }

}