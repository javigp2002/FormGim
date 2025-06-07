package com.appgim.domain.main.home.models.dataform

data class SingleOptionStatsModel(
    val id: Int,
    val question: String,
    val opciones: List<String>,
    var seleccion: Map<Int, Int> = emptyMap(),
)
