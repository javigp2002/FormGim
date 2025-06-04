package com.example.formgim.presentation.main.home.tabs.new_form

import ListFormsLazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NewFormsTab(
    modifier: Modifier = Modifier,
    goToDetail: (formId: Int) -> Unit = {},
    newFormsTabVM: NewFormsTabVM = hiltViewModel(),
) {
    val homeListState by newFormsTabVM.listFormsState.collectAsState()

    LaunchedEffect(Unit) {
        newFormsTabVM.updateListState()
    }

    ListFormsLazyColumn(
        modifier = modifier,
        form = homeListState.forms,
        goToDetail = goToDetail
    )
}
