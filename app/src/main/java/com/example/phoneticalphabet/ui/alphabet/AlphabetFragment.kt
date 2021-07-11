package com.example.phoneticalphabet.ui.alphabet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneticalphabet.R
import com.example.phoneticalphabet.models.Alphabet
import com.example.phoneticalphabet.recycler.Recycler
import com.example.phoneticalphabet.recycler.TapListener
import com.example.phoneticalphabet.ui.learn.LearnViewModel
import kotlinx.android.synthetic.main.alphabet_fragment.*

class AlphabetFragment : Fragment(), TapListener {

    private lateinit var viewModel: LearnViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(LearnViewModel::class.java)
        return inflater.inflate(R.layout.alphabet_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderUi(viewModel.alphabet)
    }

    fun renderUi(alphabet: List<Alphabet>) {
        val productsAdapter = Recycler.Adapter(alphabet)
        productsAdapter.tapListener = this
        productsAdapter.register(
            Recycler.Type(
                Alphabet::class,
                ViewType.FIELD.ordinal,
                R.layout.alphabet_row,
                bind = { itemView, renderModel, pos, tapListener ->
                    AlphabetAdapter.render(itemView, renderModel, pos, tapListener)
                })
        )

        alphabetRecyclerView.apply {
            adapter = productsAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun onTap(position: Int) {
        println("TAPPED")
    }
}