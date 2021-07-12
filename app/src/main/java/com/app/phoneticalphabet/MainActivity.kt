package com.app.phoneticalphabet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.phoneticalphabet.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.AppTheme)

        setContentView(R.layout.main_activity)
    }
}