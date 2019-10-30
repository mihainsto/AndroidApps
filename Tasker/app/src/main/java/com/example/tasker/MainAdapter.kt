package com.example.tasker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_row.view.*

class MainAdapter(val tasks_recived: MutableList<String>): RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        //return tasks.size
        return tasks_recived.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.task_row, parent, false)

        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val task = tasks_recived[position]
        holder.view.taskRow_task_content.text = task
    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}