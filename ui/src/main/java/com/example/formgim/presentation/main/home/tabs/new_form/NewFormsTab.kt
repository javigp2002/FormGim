package com.example.formgim.presentation.main.home.tabs.new_form

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.appgim.domain.main.home.models.HomeFormCard
import com.example.formgim.presentation.main.home.components.HomeCard

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

    LazyColumn(
        modifier = modifier
    ) {
        items(homeListState.forms.size) { index ->
            HomeCard(
                homeFormCard = HomeFormCard(
                    id = homeListState.forms[index].id,
                    title = homeListState.forms[index].title,
                    author = homeListState.forms[index].author,
                ),
                onClick = {
                    goToDetail(homeListState.forms[index].id)
                }
            )
        }
    }
}
