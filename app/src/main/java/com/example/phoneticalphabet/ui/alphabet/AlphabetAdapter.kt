package com.example.phoneticalphabet.ui.alphabet

import android.view.View
import com.example.phoneticalphabet.models.Alphabet
import com.example.phoneticalphabet.recycler.Recycler
import com.example.phoneticalphabet.recycler.TapListener
import kotlinx.android.synthetic.main.alphabet_row.view.*
import java.util.*

enum class ViewType {
    FIELD
}

object AlphabetAdapter : Recycler.Renderer<Alphabet> {
    override fun render(itemView: View, rm: Alphabet, pos: Int, tapListener: TapListener?) {

        itemView.letterRowTextView.text = rm.letter
        itemView.wordRowTextView.text = rm.word.capitalize(Locale.ROOT)

        itemView.setOnClickListener {
            tapListener?.onTap(pos)
        }
    }
}