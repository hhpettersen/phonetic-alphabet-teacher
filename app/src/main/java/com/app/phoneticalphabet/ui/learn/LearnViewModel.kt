package com.app.phoneticalphabet.ui.learn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.phoneticalphabet.models.Alphabet
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LearnViewModel @Inject constructor() : ViewModel() {

    var natoAlphabet = listOf(
        Pair("A", "alpha"),
        Pair("B", "bravo"),
        Pair("C", "charlie"),
        Pair("D", "delta"),
        Pair("E", "echo"),
        Pair("F", "foxtrot"),
        Pair("G", "golf"),
        Pair("H", "hotel"),
        Pair("I", "india"),
        Pair("J", "juliett"),
        Pair("K", "kilo"),
        Pair("L", "lima"),
        Pair("M", "mike"),
        Pair("N", "november"),
        Pair("O", "oscar"),
        Pair("P", "papa"),
        Pair("Q", "quebec"),
        Pair("R", "romeo"),
        Pair("S", "sierra"),
        Pair("T", "tango"),
        Pair("U", "uniform"),
        Pair("V", "victor"),
        Pair("W", "whiskey"),
        Pair("X", "x-ray"),
        Pair("Y", "yankee"),
        Pair("Z", "zulu")
    )

    var alphabet: List<Alphabet> = natoAlphabet.map {
        Alphabet(it.first, it.second)
    }

    var shuffledAlphabet = alphabet.shuffled()

    private val _position = MutableLiveData<Int>()
    val position: LiveData<Int>
        get() = _position

    init {
        _position.value = 0
    }

    fun onNextClick() {
        _position.value = (_position.value)?.plus(1)
    }

    fun onRestartClick() {
        _position.value = 0
        shuffledAlphabet = shuffledAlphabet.shuffled()
    }
}