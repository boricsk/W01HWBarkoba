package com.example.w01hwbarkoba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.w01hwbarkoba.databinding.ActivityMainBinding
import java.util.Date
import java.util.Random
import kotlin.system.measureTimeMillis


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var rand = Random()
    private var num = rand.nextInt(100)
    private var triedNums = mutableListOf<String>()
    private var triedTimes: Int = 0
    private var score: Int = 1000
    private var startTime = System.currentTimeMillis()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        newGame()
        binding.btnCheck.setOnClickListener{
            onClick()
        }
        binding.btnNewGame.setOnClickListener{
            newGame()
        }
    }
    private fun newGame(){
        num = rand.nextInt(100)
        score = 1000
        triedTimes = 0
        triedNums.clear()
        binding.tvScore.text = "Your score is : ${score.toString()}"
        println("Hint : $num")
        binding.tiTipp.setText("")
        binding.tvStat.text = ""
        binding.tvResult.text = ""
        startTime = System.currentTimeMillis()
    }
    private fun setValues(){
        triedNums.add(binding.tiTipp.text.toString())
        binding.tiTipp.setText("")
        triedTimes ++
        score -= 10
        binding.tvScore.text = "Your score is : ${score.toString()}"
    }
    private fun onClick(){
        when (binding.tiTipp.text.toString().toInt()){
            num -> {
                var duration = (System.currentTimeMillis() - startTime) / 1000
                binding.tvResult.text = "Congrat! You won!"
                binding.tvStat.text = "Your numbers were :\n${triedNums.toString()} \n\n" +
                        "Your tried $triedTimes times\n\n" +
                        "Spent time : ${duration.toString()} seconds."
            }
            in 0..num  -> {
                binding.tvResult.text = "UP..."
                setValues()
            }
            in num .. 100 -> {
                binding.tvResult.text = "DOWN..."
                setValues()
            }
            in 100..Int.MAX_VALUE -> {
                binding.tvResult.text = "Range is 1-99!"
                setValues()
            }
        }
    }
}