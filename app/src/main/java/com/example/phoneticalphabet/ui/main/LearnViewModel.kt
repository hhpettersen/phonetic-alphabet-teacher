package com.example.phoneticalphabet.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LearnViewModel : ViewModel() {

    var natoAlphabet = listOf(
        Pair("A", "alpha"),
        Pair("B", "bravo"),
        Pair("C", "delta"),
//        Pair("A", "alpha"),
//        Pair("B", "bravo"),
//        Pair("C", "delta"),
//        Pair("A", "alpha"),
//        Pair("B", "bravo"),
//        Pair("C", "delta"),
//        Pair("A", "alpha"),
//        Pair("B", "bravo"),
//        Pair("C", "delta"),
//        Pair("A", "alpha"),
//        Pair("B", "bravo"),
//        Pair("C", "delta"),
//        Pair("A", "alpha"),
//        Pair("B", "bravo"),
//        Pair("C", "delta"),
//        Pair("A", "alpha"),
//        Pair("B", "bravo"),
//        Pair("C", "delta"),
    ).shuffled()

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
        natoAlphabet = natoAlphabet.shuffled()
    }
}