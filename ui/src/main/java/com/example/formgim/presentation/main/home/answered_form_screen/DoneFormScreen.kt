package com.example.formgim.presentation.main.home.answered_form_screen

import MyTopAppBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.formgim.presentation.main.home.components.form.data_components.ChooseDataComposable

@Composable
fun DoneFormScreen(
    formId: Int,
    goBack: () -> Unit,
    viewModel: DoneFormVM = hiltViewModel()
) {
    val listFormState by viewModel.stateOfView.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getListOfQuestionsFromFormId(formId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopAppBar(
                title = "Mis respuestas",
                backEvent = { goBack() }
            )
        }
    ) { innerPadding ->
        if (listFormState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding.calculateTopPadding())
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, innerPadding.calculateTopPadding(), 0.dp, 0.dp)
            ) {
                items(listFormState.forms.size) { index ->
                    ChooseDataComposable(
                        listFormState.forms[index]
                    )
                }
            }
        }
    }


}


