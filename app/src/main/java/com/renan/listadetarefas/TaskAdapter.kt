package com.renan.listadetarefas

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.renan.listadetarefas.databinding.ResItemTaskBinding

class TaskAdapter(
    private val tasks : MutableList<Task>

) :RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(
        itemView: ResItemTaskBinding
    ) : RecyclerView.ViewHolder(itemView.root) {

        private val imgBtnDeleteTask : ImageButton
        private val clTask : ConstraintLayout
        private val tvTitleTask :TextView

        init {
            imgBtnDeleteTask = itemView.imgBtnDeleteTask
            clTask = itemView.clTask
            tvTitleTask = itemView.tvTitleTask

        }

        fun bind(task:Task){
            tvTitleTask.text = task.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ResItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(
            tasks[position]
        )
    }

    override fun getItemCount(): Int {
        return tasks.size
    }


}