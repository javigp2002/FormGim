package com.example.formgim.presentation.main.home.form_to_fill

import FormHeader
import MyAlertDialog
import MyLazyColumn
import MyTopAppBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.formgim.R
import com.example.formgim.presentation.main.home.components.form.showing_question_type.ChooseQuestionTypeComposable
import com.example.formgim.ui.theme.Constants

@Composable
fun FormToFillScreen(
    formId: Int,
    goBack: () -> Unit,
    viewModel: FormToFillVm = hiltViewModel()
) {
    val listFormState by viewModel.stateOfView.collectAsState()


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopAppBar(
                title = stringResource(R.string.form_to_fill),
                backEvent = { goBack() },
                actions = {
                    IconButton(
                        onClick = { viewModel.submitValues() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Enviar Formulario"
                        )
                    }
                }
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

                items(
                    listFormState.form.questions.size,
                    key = { listFormState.form.questions[it].id }) { index ->
                    val formItem = listFormState.form.questions[index]
                    ChooseQuestionTypeComposable(
                        formItem,
                        onAnswerChanged = { answer -> viewModel.updateAnswer(index, answer) },
                        onMultipleChanged = { answer ->
                            viewModel.updateMultipleSelection(
                                index,
                                answer
                            )
                        },
                        onSingleChanged = { selectedOption: Int ->
                            viewModel.updateSingleSelection(
                                index,
                                selectedOption
                            )
                        },
                        onSliderChanged = { sliderValue: Float ->
                            viewModel.updateSliderAnswer(
                                index,
                                sliderValue
                            )
                        },
                        readonly = false
                    )
                }
            }

            if (listFormState.error) {
                MyAlertDialog(
                    acceptOption = { viewModel.dismissDialog() },
                )
            }

            if (listFormState.showAlertDialog) {
                MyAlertDialog(
                    acceptOption = {
                        viewModel.dismissDialog()
                        goBack()
                    },
                    title = stringResource(R.string.form_to_fill_perfecto),
                    message = stringResource(R.string.guardado_con_exito),

                    )
            }
        }
    }


}


