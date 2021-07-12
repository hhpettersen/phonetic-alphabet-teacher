package com.app.phoneticalphabet.models
import com.app.phoneticalphabet.recycler.Recycler

data class Alphabet(val letter: String, val word: String) : Recycler.RenderModel