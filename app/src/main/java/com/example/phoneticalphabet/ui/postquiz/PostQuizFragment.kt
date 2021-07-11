package com.example.phoneticalphabet.ui.postquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.phoneticalphabet.databinding.PostQuizFragmentBinding

//@AndroidEntryPoint
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

        ui(score, 11)



//        viewModel.res.observe(viewLifecycleOwner, { resource ->
//            when (resource.status) {
//                Status.SUCCESS -> {
//                    resource.data.let { highScores ->
//                        val highScore = when (quizType) {
//                            QuizType.SMALL -> highScores?.smallHighScore
//                            QuizType.LARGE -> highScores?.largeHighScore
//                        } ?: 0
//                        Ui(score, highScore)
//                        updateHighScore(score, highScore, highScores, quizType)
//                    }
//                }
//                Status.LOADING -> Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
//                Status.ERROR -> Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
//            }
//        })

        binding.scoreText.text = "Your score: $score"

        binding.playAgainButton.setOnClickListener {
            findNavController().navigate(PostQuizFragmentDirections.actionPostQuizFragmentToQuizFragment())
        }

        binding.navigateHomeButton.setOnClickListener {
            findNavController().navigate(PostQuizFragmentDirections.actionPostQuizFragmentToDashBoardFragment())
        }
    }

    private fun updateHighScore(score: Int, highScore: Int) {
        if (score > highScore) {
//            viewModel.insertHighScore(score)
        }
    }

    private fun ui (score: Int, highScore: Int) {
        if (score > highScore) {
            binding.scoreInfoText.text = "You just set a new highscore, congratulations!"
//            binding.lottieAnimationView.setAnimation(R.raw.fireworks)
        } else {
            binding.scoreInfoText.text = "Keep trying to beat your highscore!"
//            binding.lottieAnimationView.setAnimation(R.raw.dog_run)
        }
    }

}