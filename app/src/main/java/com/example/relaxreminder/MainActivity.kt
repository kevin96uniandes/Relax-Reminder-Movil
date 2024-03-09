package com.example.relaxreminder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.relaxreminder.databinding.ActivityLoginBinding
import com.example.relaxreminder.databinding.ActivityMainBinding
import com.example.relaxreminder.ui.LoginActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
