package com.renan.listadetarefas

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.renan.listadetarefas.databinding.ResItemTaskBinding

class TaskAdapter(
    private val onClick : (Task) -> Unit,
    private val onDeleteClick: (Task) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val tasks = mutableListOf<Task>()

    inner class TaskViewHolder(
        itemView: ResItemTaskBinding
    ) : RecyclerView.ViewHolder(itemView.root) {

        //Elemento que vão ser alterados
        private val imgBtnDeleteTask: ImageButton
        private val clTask: ConstraintLayout
        private val tvTitleTask: TextView

        init {
            imgBtnDeleteTask = itemView.imgBtnDeleteTask
            clTask = itemView.clTask
            tvTitleTask = itemView.tvTitleTask

        }

        fun bind(
            task: Task,
            onClick: (Task) -> Unit,
            onDeleteClick: (Task) -> Unit

        ) {
            tvTitleTask.text = task.title

            imgBtnDeleteTask.setOnClickListener {
                onDeleteClick(task)
            }

            clTask.setOnClickListener {
                onClick(task)
            }

            if(task.done){

                tvTitleTask.setTextColor(ContextCompat.getColor(itemView.context,R.color.white))

            }else{
                tvTitleTask.setTextColor(ContextCompat.getColor(itemView.context,R.color.black))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        //Modelo fixo para criar ViewHolder
        return TaskViewHolder(
            ResItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        //Usa a funçaõ bind do TaskViewHolder e o position na list de task que vem pelo construtor do ADAPTER
        holder.bind(
            tasks[position],
            onClick,
            onDeleteClick

        )
    }

    override fun getItemCount(): Int {
        return tasks.size
    }


    fun addTask(task: Task) {
        tasks.add(task)
        //Esta notificando o Recycler que a ultima posição mudou ! (tamanho total - 1 pq o array começa no zero)
        notifyItemInserted(tasks.size - 1)
        return

    }

    fun deleteTask(task: Task){
        //Pegar a posição do item deletado para passar no notifyItemRemoved
        val deletedPosition = tasks.indexOf(task)
        tasks.remove(task)
        notifyItemRemoved(deletedPosition)
    }

    fun updateTask(task: Task) {

        val updatePosition = tasks.indexOf(task)
        tasks[updatePosition] = task
        notifyItemChanged(updatePosition)

    }


}