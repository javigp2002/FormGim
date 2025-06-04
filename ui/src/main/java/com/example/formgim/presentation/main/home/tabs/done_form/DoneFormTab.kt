package com.example.formgim.presentation.main.home.tabs.done_form

import ListFormsLazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DoneForm(
    modifier: Modifier = Modifier,
    goToDetail: (formId: Int) -> Unit = {},
    doneFormTabVm: DoneFormTabVm = hiltViewModel(),
) {
    val homeListState by doneFormTabVm.listFormsState.collectAsState()

    LaunchedEffect(Unit) {
        doneFormTabVm.updateListState()
    }

    ListFormsLazyColumn(
        modifier = modifier,
        form = homeListState.forms,
        goToDetail = goToDetail
    )
}
