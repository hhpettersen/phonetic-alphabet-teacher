package com.app.phoneticalphabet.ui.learn

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.phoneticalphabet.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.learn_fragment.*

@AndroidEntryPoint
class LearnFragment : Fragment() {

    companion object {
        fun newInstance() = LearnFragment()
    }

    private val viewModel: LearnViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.learn_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        viewModel.position.observe(viewLifecycleOwner, { pos ->
            wordFilterView.visibility = View.VISIBLE
            revealButton.visibility = View.VISIBLE
            nextButton.visibility = View.GONE
            restartButton.visibility = View.GONE
            dashBoardButton.visibility = View.GONE
            completeText.visibility = View.GONE
            letterTextView.text = viewModel.shuffledAlphabet[pos].letter
            wordTextView.text = viewModel.shuffledAlphabet[pos].word.toUpperCase()

            revealButton.setOnClickListener {
                wordFilterView.visibility = View.GONE
                nextButton.visibility = View.VISIBLE
                wordTextView.visibility = View.VISIBLE
                revealButton.visibility = View.GONE
                if(pos >= viewModel.shuffledAlphabet.size - 1) {
                    restartButton.visibility = View.VISIBLE
                    dashBoardButton.visibility = View.VISIBLE
                    completeText.visibility = View.VISIBLE
                    nextButton.visibility = View.GONE
                }
            }

            if (pos < viewModel.shuffledAlphabet.size - 1) {
                nextButton.setOnClickListener {
                    viewModel.onNextClick()
                    revealButton.visibility = View.VISIBLE
                    nextButton.visibility = View.GONE
                    wordFilterView.visibility = View.VISIBLE
                    wordTextView.visibility = View.INVISIBLE
                }
            } else {
                nextButton.visibility = View.GONE
                restartButton.setOnClickListener {
                    restartButton.visibility = View.GONE
                    viewModel.onRestartClick()
                }
                dashBoardButton.setOnClickListener {
                    findNavController().navigate(
                        R.id.action_learnFragment_to_dashBoardFragment
                    )
                }
            }
        })
    }
}