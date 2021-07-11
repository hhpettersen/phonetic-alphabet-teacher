package com.example.phoneticalphabet.models
import com.example.phoneticalphabet.recycler.Recycler

data class Alphabet(val letter: String, val word: String) : Recycler.RenderModel