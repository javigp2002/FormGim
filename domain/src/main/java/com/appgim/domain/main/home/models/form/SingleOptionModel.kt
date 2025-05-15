package com.appgim.domain.main.home.models.form

data class SingleOptionModel(
    val id: Int,
    val question: String,
    val opciones: List<String>,
    var seleccion: Int = -1,
    var error: Boolean = false,
)
