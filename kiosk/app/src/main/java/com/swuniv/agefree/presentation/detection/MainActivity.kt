package com.swuniv.agefree.presentation.detection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.swuniv.agefree.databinding.ActivityMainBinding
import com.swuniv.agefree.databinding.ActivityStartBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}