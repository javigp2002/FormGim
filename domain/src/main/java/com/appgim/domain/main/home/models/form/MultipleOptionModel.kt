package com.appgim.domain.main.home.models.form

data class MultipleOptionModel(
    val id: Int,
    val question: String,
    val opciones: List<String>,
    var seleccion: Int? = null
)
