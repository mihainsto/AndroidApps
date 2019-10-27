package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var diceImage: ImageView
    lateinit var diceImage2: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diceImage = findViewById(R.id.dice_image)
        diceImage2 = findViewById(R.id.dice_image2)
        val rollButton: Button = findViewById(R.id.roll_button)
        val resetButton: Button = findViewById(R.id.reset_button)
        rollButton.setOnClickListener{rollDice()}
        resetButton.setOnClickListener{resetBtn()}

    }

    private fun getRandomImage(): Int{
        val randomInt = Random.nextInt(6) + 1
        val drawableResource = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        return drawableResource
    }
    private fun rollDice(){

        //val resultText: TextView = findViewById(R.id.result_text)
        //val diceImage: ImageView = findViewById(R.id.dice_image)

        //resultText.text = rendomInt.toString()
        var drawableResource = getRandomImage()
        diceImage.setImageResource(drawableResource)
        drawableResource = getRandomImage()
        diceImage2.setImageResource(drawableResource)

    }

    private fun countUp(){
        /*val resultText: TextView = findViewById(R.id.result_text)
        var number = Integer.parseInt(resultText.text.toString())
        number += 1
        if (number > 6) {
            number = 1
        }
        resultText.text=number.toString()*/

    }
    private fun resetBtn(){
        diceImage2.setImageResource(R.drawable.empty_dice)
        diceImage.setImageResource(R.drawable.empty_dice)
    }
}

