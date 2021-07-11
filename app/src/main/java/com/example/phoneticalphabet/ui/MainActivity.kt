package com.example.phoneticalphabet.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.phoneticalphabet.R
import com.example.phoneticalphabet.ui.quiz.QuizWords
import java.io.File
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}