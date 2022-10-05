package com.renan.listadetarefas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.renan.listadetarefas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Inicializando o viewBindind
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inflando o bind para pode usa-lo
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //inicializa o adapter
        setupAdapter()
        //adiciona task randons apenas para visualizar
        setupLayout()

    }

    private fun setupLayout() {
        //Botão Adicionar Task
        binding.fab.setOnClickListener {
            var rand = (1..1_000).random()
            adapter.addTask(Task("Task $rand", "Descrição task teste"))
        }
    }

    private fun setupAdapter() {

        //Como no construtor foi colocado uma funçao UNIT que recebe uma TASK, ao se criar um TaskAdapter pode colocar um função junto que utilize TASK
        adapter = TaskAdapter(onDeleteClick = {
            //Deleta Task
                task -> adapter.deleteTask(task)
        },
            onClick = {task ->
                //Ao clicar na Task inverte o status de feita ou não feita
                task.done = !(task.done)

                adapter.updateTask(task)

            }
        )
        //Vinculando o adapter
        binding.rvTasks.adapter = adapter
    }
}