package com.example.formgim.presentation.main.home.answered_form_screen

import FormHeader
import MyElevatedCard
import MyLazyColumn
import MyTopAppBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.formgim.presentation.main.home.components.form.showing_question_type.ChooseQuestionTypeComposable
import com.example.formgim.ui.theme.Constants

@Composable
fun DoneFormScreen(
    formId: Int,
    goBack: () -> Unit,
    viewModel: DoneFormVM = hiltViewModel()
) {
    val listFormState by viewModel.stateOfView.collectAsState()

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
            MyLazyColumn(innerPadding = innerPadding) {
                item {
                    FormHeader(
                        title = listFormState.form.title,
                        description = listFormState.form.description,
                        modifier = Modifier.padding(
                            horizontal = Constants.PaddingSizes.S.dp,
                            vertical = Constants.PaddingSizes.M.dp
                        )
                    )
                }

                items(listFormState.form.questions.size) { index ->
                    MyElevatedCard {
                        ChooseQuestionTypeComposable(
                            listFormState.form.questions[index],
                            readonly = true,
                        )
                    }
                }
            }
        }
    }
}


