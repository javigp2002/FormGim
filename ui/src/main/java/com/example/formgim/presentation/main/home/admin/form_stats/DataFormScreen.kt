package com.example.formgim.presentation.main.home.admin.form_stats

import FormHeader
import MyElevatedCard
import MyLazyColumn
import MyTopAppBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
            MyLazyColumn(innerPadding = innerPadding) {
                item {
                    FormHeader(
                        title = listFormState.dataStats.title,
                        description = listFormState.dataStats.description,
                        modifier = Modifier.padding(
                            horizontal = Constants.PaddingSizes.S.dp,
                            vertical = Constants.PaddingSizes.M.dp
                        )
                    )
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
