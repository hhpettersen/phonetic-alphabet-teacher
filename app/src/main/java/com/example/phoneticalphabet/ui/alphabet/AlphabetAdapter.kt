package com.example.phoneticalphabet.ui.alphabet

import android.view.View
import com.example.phoneticalphabet.recycler.Recycler
import com.example.phoneticalphabet.recycler.TapListener
import com.example.phoneticalphabet.ui.learn.Alphabet
import kotlinx.android.synthetic.main.alphabet_row.view.*

enum class ViewType {
    FIELD
}

object AlphabetAdapter : Recycler.Renderer<Alphabet> {
    override fun render(itemView: View, rm: Alphabet, pos: Int, tapListener: TapListener?) {

        itemView.letterRowTextView.text = rm.letter
        itemView.wordRowTextView.text = rm.word

        itemView.setOnClickListener {
            tapListener?.onTap(pos)
        }
    }
}