package com.appgim.domain.main.home.models.form

data class TextBoxModel(
    val id: Int,
    val title: String,
    var answer: String = "",
    var error: Boolean = false
)
