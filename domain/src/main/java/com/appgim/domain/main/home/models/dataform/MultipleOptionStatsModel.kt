package com.appgim.domain.main.home.models.dataform

data class MultipleOptionStatsModel(
    val id: Int,
    val question: String,
    val opciones: List<String>,
    var seleccion: Map<Int, Int> = emptyMap(),
)
