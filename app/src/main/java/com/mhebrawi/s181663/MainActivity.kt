package com.mhebrawi.s181663

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mhebrawi.s181663.databinding.ActivityMainBinding


lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}