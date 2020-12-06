package com.example.phoneticalphabet.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.phoneticalphabet.R
import kotlinx.android.synthetic.main.learn_fragment.*

class LearnFragment : Fragment() {

    companion object {
        fun newInstance() = LearnFragment()
    }

    private lateinit var viewModel: LearnViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProviders.of(this).get(LearnViewModel::class.java)
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
            letterTextView.text = viewModel.natoAlphabet[pos].first
            wordTextView.text = viewModel.natoAlphabet[pos].second.toUpperCase()

            revealButton.setOnClickListener {
                wordFilterView.visibility = View.GONE
                nextButton.visibility = View.VISIBLE
                wordTextView.visibility = View.VISIBLE
                revealButton.visibility = View.GONE
                if(pos >= viewModel.natoAlphabet.size - 1) restartButton.visibility = View.VISIBLE
            }

            if (pos < viewModel.natoAlphabet.size - 1) {
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
            }
        })
    }
}