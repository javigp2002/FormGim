package com.appgim.domain.main.home.models.dataform

data class TextDataModel(
    val id: Int,
    val title: String,
    var answers: List<String> = emptyList(),
    var error: Boolean = false
)
