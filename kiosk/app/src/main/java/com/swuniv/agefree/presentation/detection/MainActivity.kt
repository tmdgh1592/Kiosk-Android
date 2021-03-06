package com.swuniv.agefree.presentation.detection

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.swuniv.agefree.BuildConfig
import com.swuniv.agefree.R
import com.swuniv.agefree.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        var age: Int = intent.getIntExtra("age", 1)
        //TODO age 설정하여 원하는 Fragment 진입

        if (BuildConfig.DEBUG) {
            Log.d("++User Age", "onCreate: $age")
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        if (age >= 50) {
            navController.navigate(R.id.oldSelectMenuFragment)
        }
//        else {
//            navController.navigate(R.id.defaultMenuFragment)
//        }

    }
}


