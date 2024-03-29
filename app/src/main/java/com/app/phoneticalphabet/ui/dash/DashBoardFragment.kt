package com.app.phoneticalphabet.ui.dash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.phoneticalphabet.R
import com.app.phoneticalphabet.databinding.DashBoardFragmentBinding
import com.app.phoneticalphabet.helpers.Status
import com.app.phoneticalphabet.models.HighScore
import com.app.phoneticalphabet.models.ScoreTier
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardFragment : Fragment() {

    private val viewModel: DashBoardViewModel by viewModels()

    private var _binding: DashBoardFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DashBoardFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.res.observe(viewLifecycleOwner, { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data.let { highScore ->
                        // If it is the first time the user uses the app, the highscore will be set
                        if (highScore == null) {
                            viewModel.insertHighScores(HighScore())
                            binding.highScoreText.text = "Play the quiz to test your abilities!"
                        } else {
                            if (highScore.score == 0) {
                                binding.highScoreText.visibility = View.GONE
                            } else {
                                binding.medalImageView.visibility = View.VISIBLE
                                colorMedal(highScore.score)
                                binding.highScoreText.text =
                                    if (highScore.score == 26) "All correct answers!" else "High score: ${highScore.score}/26"
                            }
                        }
                    }
                }
                Status.LOADING -> Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                Status.ERROR -> Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        })

        binding.alphabetButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_dashBoardFragment_to_alphabetFragment
            )
        }

        binding.learnButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_dashBoardFragment_to_learnFragment
            )
        }

        binding.quizButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_dashBoardFragment_to_quizFragment
            )
        }
    }

    private fun colorMedal(score: Int) {
        val scoreTier = ScoreTier.getScoreTier(score)
        val tierColor = ScoreTier.getTierTint(scoreTier)
        binding.medalImageView.drawable.setTint(
            ResourcesCompat.getColor(
                resources,
                tierColor,
                null
            )
        )
    }
}