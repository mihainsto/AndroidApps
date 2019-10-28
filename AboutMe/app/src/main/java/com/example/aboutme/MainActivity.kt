package com.example.aboutme

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    lateinit var nicknameView: TextView
    lateinit var inputText: EditText
    lateinit var doneButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doneButton = findViewById(R.id.done_button)
        nicknameView = findViewById(R.id.nickname_toUpdate_View)
        inputText = findViewById(R.id.nickname_edit)

        doneButton.setOnClickListener{updateTextView()}
        nicknameView.setOnClickListener{resetEnv()}

    }

    private fun updateTextView(){
        inputText.visibility = View.GONE
        doneButton.visibility = View.GONE
        nicknameView.visibility = View.VISIBLE

        val input = inputText.text
        nicknameView.text = input
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(doneButton.windowToken, 0)
    }

    private fun resetEnv(){
        inputText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        nicknameView.visibility = View.GONE
    }
}
