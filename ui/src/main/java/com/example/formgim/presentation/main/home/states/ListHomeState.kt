package com.example.formgim.presentation.main.home.states

import com.appgim.domain.main.home.models.HomeFormCard

data class ListHomeState(
    val isLoading: Boolean = false,
    val error: String = "",
    val forms: List<HomeFormCard> = emptyList<HomeFormCard>(),
    val isAdmin: Boolean = false
)
