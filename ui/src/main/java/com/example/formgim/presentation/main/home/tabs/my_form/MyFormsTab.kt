package com.example.formgim.presentation.main.home.tabs.my_form

import ListFormsLazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MyFormsTab(
    modifier: Modifier = Modifier,
    goToDetail: (formId: Int) -> Unit = {},
    myFormsVm: MyFormsTabVm = hiltViewModel(),
) {
    val homeListState by myFormsVm.listFormsState.collectAsState()

    LaunchedEffect(Unit) {
        myFormsVm.updateListState()
    }

    ListFormsLazyColumn(
        modifier = modifier,
        form = homeListState.forms,
        goToDetail = goToDetail
    )
}
