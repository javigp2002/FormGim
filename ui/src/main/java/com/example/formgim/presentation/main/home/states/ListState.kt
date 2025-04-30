package com.example.formgim.presentation.main.home.states

import com.appgim.domain.main.home.models.HomeFormCard

data class ListState(
    var isLoading: Boolean = false,
    var error: String = "",
    var forms: List<HomeFormCard> = emptyList<HomeFormCard>()
)
