package com.example.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun makeColored(view: View){
        when (view.id){
            R.id.box1 -> view.setBackgroundColor(Color.DKGRAY)
            R.id.box2 -> view.setBackgroundColor(Color.GRAY)
            R.id.box3 -> view.setBackgroundColor(Color.BLUE)
            R.id.box4 -> view.setBackgroundColor(Color.MAGENTA)
            R.id.box5 -> view.setBackgroundColor(Color.BLUE)
            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }

    private fun setListeners(){
        val box1: TextView = findViewById(R.id.box1)
        val box2: TextView = findViewById(R.id.box2)
        val box3: TextView = findViewById(R.id.box3)
        val box4: TextView = findViewById(R.id.box4)
        val box5: TextView = findViewById(R.id.box5)

        val rootConstraintLayout: View = findViewById(R.id.constraint_layout)

        val clickableViews: List<View> =
            listOf(box1,box2,box3,box4,box5)

        for (item  in clickableViews){
            item.setOnClickListener{makeColored(it)}
        }

    }
}
