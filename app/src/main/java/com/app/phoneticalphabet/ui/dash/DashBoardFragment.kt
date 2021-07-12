package com.app.phoneticalphabet.ui.dash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.phoneticalphabet.R
import com.app.phoneticalphabet.databinding.DashBoardFragmentBinding
import com.app.phoneticalphabet.helpers.Status
import com.app.phoneticalphabet.models.HighScore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dash_board_fragment.view.*

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
                    resource.data.let { highScores ->
//                        val smallHighScore: Int = highScores?.smallHighScore ?: 0
//                        val largeHighScore: Int = highScores?.largeHighScore ?: 0
//                        binding.smallQuizScoreText.text = "High score: $smallHighScore"
//                        binding.largeQuizScoreText.text = "High score: $largeHighScore"
                        // If it is the first time the user uses the app, the highscore will be set
                        if (highScores == null) viewModel.insertHighScores(HighScore())
//                        colorCup(smallHighScore, largeHighScore)
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
}