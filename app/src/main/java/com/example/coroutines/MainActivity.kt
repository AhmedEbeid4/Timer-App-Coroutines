package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CorViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel=ViewModelProvider(this)[CorViewModel::class.java]
        viewModel.counter.observe(this, Observer { int->
            binding.text.text=int.toString()
        })
        binding.startBtn.setOnClickListener {
            viewModel.onStart()
            binding.startBtn.isEnabled=false
        }
        binding.pauseBtn.setOnClickListener {
            viewModel.onPause()
            binding.startBtn.isEnabled=true
        }
        binding.stopBtn.setOnClickListener {
            viewModel.onStop()
            binding.startBtn.isEnabled=true
        }
    }

}