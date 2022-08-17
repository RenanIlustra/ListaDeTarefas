package com.renan.listadetarefas

data class Task(
    val title: String,
    val description: String,
    var done: Boolean
)
