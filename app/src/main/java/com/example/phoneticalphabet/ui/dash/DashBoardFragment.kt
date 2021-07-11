package com.example.phoneticalphabet.ui.dash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.phoneticalphabet.R
import kotlinx.android.synthetic.main.dash_board_fragment.view.*
import java.io.File
import java.io.InputStream

class DashBoardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dash_board_fragment, container, false)

        view.alphabetButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_dashBoardFragment_to_alphabetFragment
            )
        }

        view.learnButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_dashBoardFragment_to_learnFragment
            )
        }

        view.quizButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_dashBoardFragment_to_quizFragment
            )
        }

        return view
    }
}