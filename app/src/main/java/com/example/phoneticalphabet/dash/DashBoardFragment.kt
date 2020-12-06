package com.example.phoneticalphabet.dash

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.phoneticalphabet.R
import kotlinx.android.synthetic.main.dash_board_fragment.view.*

class DashBoardFragment : Fragment() {

    companion object {
        fun newInstance() = DashBoardFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dash_board_fragment, container, false)

        view.learnButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_dashBoardFragment_to_learnFragment
            )
        }

        return view
    }
}