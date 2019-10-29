package com.example.tasker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception
import java.net.URL
import java.security.AccessController.getContext
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    lateinit var loginButton: Button
    lateinit var singupButton: Button
    lateinit var forgotHyperlink: TextView
    lateinit var submitButton: Button

    lateinit var emailInput: EditText
    lateinit var passwordInput: EditText

    var curentPage = "Login"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton = findViewById(R.id.loginButton)
        singupButton = findViewById(R.id.singupButton)
        forgotHyperlink = findViewById(R.id.forgotHyperlink)
        submitButton = findViewById(R.id.submitButton)
        emailInput = findViewById(R.id.inputEmail)
        passwordInput = findViewById(R.id.inputPassword)

        loginButton.setOnClickListener{ChangePageButtonClicked()}
        singupButton.setOnClickListener{ChangePageButtonClicked()}
        submitButton.setOnClickListener{submitButtonClicked()}
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
    private fun submitButtonClicked(){
        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()
        if (email == "" || password == "" ){
            return
        }

        var api_result = null


        if(curentPage == "Login") {
            val api_url = "http://40.113.129.201/auth/$email/$password"
            doAsync {
                try {
                    val result = URL(api_url).readText()
                    //Log.d("Result","Result: ${result.toString()}")
                    uiThread {
                        recivedLoginApiResponse(result.toString())
                    }
                } catch (e: Exception) {
                    Log.d("Exception", "Exception: ${e.toString()}")

                }
            }
        }
        else{
            val api_url = "http://40.113.129.201/register/$email/$password"
            doAsync {
                try {
                    val result = URL(api_url).readText()
                    //Log.d("Result","Result: ${result.toString()}")
                    uiThread {
                        recivedSingupApiResponse(result.toString())
                    }
                } catch (e: Exception) {
                    Log.d("Exception", "Exception: ${e.toString()}")

                }
            }
        }

    }
    private fun recivedLoginApiResponse(response: String){
        Log.d("Result","Result: ${response.toString()}")
        Toast.makeText(this@MainActivity, response.toString(), Toast.LENGTH_SHORT).show()

    }
    private fun recivedSingupApiResponse(response: String){
        Log.d("Result","Result: ${response.toString()}")
        Toast.makeText(this@MainActivity, response.toString(), Toast.LENGTH_SHORT).show()
    }
}
