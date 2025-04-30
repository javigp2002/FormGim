package com.example.formgim.presentation.main.home.components.models

data class HomeFormCard(
    var id: Int,
    var title: String,
    var author: String,
    var onClick: () -> Unit
)