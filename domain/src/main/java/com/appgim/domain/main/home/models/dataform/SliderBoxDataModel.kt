package com.appgim.domain.main.home.models.dataform

data class SliderBoxDataModel(
    val id: Int,
    val question: String,
    var answers: List<Float> = emptyList(),
    var error: Boolean = false,
)
