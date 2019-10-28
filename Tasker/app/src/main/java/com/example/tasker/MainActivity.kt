package com.example.tasker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import java.security.AccessController.getContext


class MainActivity : AppCompatActivity() {

    lateinit var loginButton: Button
    lateinit var singupButton: Button
    lateinit var forgotHyperlink: TextView
    var curentPage = "Login"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton = findViewById(R.id.loginButton)
        singupButton = findViewById(R.id.singupButton)
        forgotHyperlink = findViewById(R.id.forgotHyperlink)

        loginButton.setOnClickListener{ChangePageButtonClicked()}
        singupButton.setOnClickListener{ChangePageButtonClicked()}
    }

    private fun ChangePageButtonClicked(){




        if (curentPage == "Login"){
            val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 240f, resources.displayMetrics)
            loginButton.setBackgroundResource(R.drawable.singupsinguppage)
            loginButton.layoutParams.width = px.toInt()

            singupButton.setBackgroundResource(R.drawable.loginsinguppage)
            forgotHyperlink.visibility = View.GONE
            curentPage = "Singup"
        }
        else{
            val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 220f, resources.displayMetrics)
            loginButton.setBackgroundResource(R.drawable.login)
            loginButton.layoutParams.width = px.toInt()

            singupButton.setBackgroundResource(R.drawable.sign_up)
            forgotHyperlink.visibility = View.VISIBLE
            curentPage = "Login"
        }

    }

}
