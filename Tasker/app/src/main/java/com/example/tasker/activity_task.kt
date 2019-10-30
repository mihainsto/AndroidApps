package com.example.tasker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_task.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.Exception
import java.net.URL

class activity_task : AppCompatActivity() {

    private var loggedUser: String? = null
    lateinit var newTaskTextView: EditText
    lateinit var newButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        loggedUser = intent.getStringExtra("loggedUser")
        apiCall_getTasks()

        newButton = findViewById(R.id.newButton_activity_task)
        newButton.setOnClickListener{newButtonClicked()}

        newTaskTextView = findViewById(R.id.editText_newtask_activity_task)
        newTaskTextView.setOnClickListener{editTextClicked()}
    }

    private fun editTextClicked(){
        newButton.visibility = View.VISIBLE
    }
    private fun newButtonClicked(){
        newButton.visibility = View.GONE
        val text = newTaskTextView.text.toString()
        if(text.length > 2)
            addTaskApiCall(text)
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(newButton.windowToken, 0)
        apiCall_getTasks()

    }
    private fun apiCall_getTasks(){
       val  link = "http://40.113.129.201/task/getTasks/$loggedUser"

        doAsync {
            try {
                val result = URL(link).readText()
                //Log.d("Result","Result: ${result.toString()}")
                uiThread {
                    recivedTasks(result.toString())
                }
            } catch (e: Exception) {
                Log.d("Exception", "Exception: ${e.toString()}")

            }
        }
    }

    private fun recivedTasks(response: String){
        Log.d("tag",response)
        val gson = GsonBuilder().create()
        val logResponse = gson.fromJson(response,tasks_response::class.java)
        logResponse.formatSpaces()
        initializeRecycleView(logResponse.tasks)

        val x = 5
    }

    private fun initializeRecycleView(tasks: MutableList<String>){

        recyclerview_task.layoutManager = LinearLayoutManager(this  )
        recyclerview_task.adapter = MainAdapter(tasks)
    }

    private fun addTaskApiCall(task: String){

        val taskEdited = task.replace(" ","_")
        val  link = "http://40.113.129.201/task/addTask/mihai/$taskEdited"

        doAsync {
            try {
                val result = URL(link).readText()
                //Log.d("Result","Result: ${result.toString()}")
                uiThread {
                    //recivedTasks(result.toString())
                }
            } catch (e: Exception) {
                Log.d("Exception", "Exception: ${e.toString()}")

            }
        }
    }
}

class tasks_response(val tasks: MutableList<String>){
    fun formatSpaces(){
        for(i in 0 until tasks.size){
            tasks[i] =  tasks[i].replace("_"," ")
        }
    }
}


