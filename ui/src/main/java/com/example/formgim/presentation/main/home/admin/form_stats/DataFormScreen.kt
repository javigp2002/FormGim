package com.example.formgim.presentation.main.home.admin.form_stats

import MyElevatedCard
import MyTopAppBar
import QuestionDescriptionText
import QuestionTitleText
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.formgim.presentation.main.home.components.form.data_components.ChooseDataComposable
import com.example.formgim.ui.theme.Constants

@Composable
fun DataFormScreen(
    onBack: () -> Unit,
    formId: Int,
    viewModel: DataFormVM = hiltViewModel()
) {
    val listFormState by viewModel.stateOfView.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Constants.PaddingSizes.M.dp,
                vertical = Constants.PaddingSizes.L.dp
            ),
        topBar = {
            MyTopAppBar(
                title = "Información del formulario",
                backEvent = { onBack() },
                actions = {}
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
                    .padding(
                        Constants.PaddingSizes.S.dp,
                        innerPadding.calculateTopPadding(),
                        Constants.PaddingSizes.S.dp,
                        0.dp
                    )
            ) {
                item {
                    QuestionTitleText(
                        questionTitle = listFormState.dataStats.title,
                        modifier = Modifier.padding(
                            horizontal = Constants.PaddingSizes.M.dp,
                            vertical = Constants.PaddingSizes.S.dp,
                        ),
                        style = typography.displaySmall
                    )
                }
                item {
                    QuestionDescriptionText(questionDescription = listFormState.dataStats.description)
                    Spacer(modifier = Modifier.height(Constants.PaddingSizes.XL.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(Constants.PaddingSizes.L.dp))

                }


                items(
                    listFormState.dataStats.questions.size,
                    key = { listFormState.dataStats.questions[it].id }
                ) { index ->
                    MyElevatedCard {
                        ChooseDataComposable(
                            questionType = listFormState.dataStats.questions[index],
                            timesFormDone = listFormState.dataStats.formNumberOfFormsDone
                        )
                    }
                }
            }
        }
    }
}
