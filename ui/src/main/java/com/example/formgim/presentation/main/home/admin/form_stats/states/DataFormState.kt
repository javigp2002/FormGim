package com.example.formgim.presentation.main.home.admin.form_stats.states

import com.appgim.domain.main.home.models.FormDataStats

data class ListDataFormState(
    val isLoading: Boolean = true,
    val dataStats: FormDataStats = FormDataStats("", "", 0, emptyList()),

    val showAlertDialog: Boolean = false,
    val hasBeenSavedProperly: Boolean = false
)
