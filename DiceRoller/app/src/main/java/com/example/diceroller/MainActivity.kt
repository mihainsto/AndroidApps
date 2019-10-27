package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.roll_button)
        val countButton: Button = findViewById(R.id.count_button)
        val resetButton: Button = findViewById(R.id.reset_button)

        rollButton.setOnClickListener{rollDice()}
        countButton.setOnClickListener{countUp()}
        resetButton.setOnClickListener{resetBtn()}
    }


    private fun rollDice(){
        val rendomInt = Random.nextInt(6) + 1
        val resultText: TextView = findViewById(R.id.result_text)
        resultText.text = rendomInt.toString()

    }
    private fun countUp(){
        val resultText: TextView = findViewById(R.id.result_text)
        var number = Integer.parseInt(resultText.text.toString())
        number += 1
        if (number > 6) {
            number = 1
        }
        resultText.text=number.toString()

    }
    private fun resetBtn(){
        val resultText : TextView= findViewById(R.id.result_text)
        resultText.text = "0"
    }
}

