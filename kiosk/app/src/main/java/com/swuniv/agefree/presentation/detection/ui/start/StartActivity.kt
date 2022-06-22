package com.swuniv.agefree.presentation.detection.ui.start

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.swuniv.agefree.databinding.ActivityStartBinding
import com.swuniv.agefree.presentation.detection.utils.showToast

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * @value
         * take in : 101
         * take out : 102
         * */
        binding.takeinBtn.setOnClickListener {
            val intent = Intent(this, FdActivity::class.java)
            intent.putExtra("take_mode", 101)
            startActivity(intent)
            showToast("포장 버튼 클릭")
        }

        binding.takeoutBtn.setOnClickListener {
            val intent = Intent(this, FdActivity::class.java)
            intent.putExtra("take_mode", 102)
            startActivity(intent)
            showToast("매장 버튼 클릭")
        }

    }

}