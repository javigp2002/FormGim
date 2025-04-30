package com.example.formgim.presentation.main.home.form_to_fill

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FormToFillScreen(viewModel: FormToFillVm = hiltViewModel()) {
    val listOfQuestions by viewModel.listOfQuestions.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getListOfQuestionsFromFormId(1)
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Greeting(
            name = "Android",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Detail $name!",
        modifier = modifier
    )
}

