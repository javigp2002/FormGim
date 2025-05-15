package com.example.formgim.presentation.main.home.form_to_fill

import MyShowErrorDialog
import MySubmitButton
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.formgim.presentation.main.home.components.form.ChooseQuestionTypeComposable

@Composable
fun FormToFillScreen(viewModel: FormToFillVm = hiltViewModel()) {
    val listFormState by viewModel.stateOfView.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getListOfQuestionsFromFormId(1)
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, innerPadding.calculateTopPadding(), 0.dp, 0.dp)
        ) {
            items(listFormState.forms.size) { index ->
                ChooseQuestionTypeComposable(
                    listFormState.forms[index],
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
                    }
                )
            }
            item {
                MySubmitButton { viewModel.submitValues() }
            }
        }

        if (listFormState.error) {
            MyShowErrorDialog { viewModel.dismissDialog() }
        }
    }


}


