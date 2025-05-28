package com.example.formgim.presentation.main.home.admin.creation_form

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.formgim.presentation.main.home.admin.creation_form.states.ListCreationFormState

@Composable
fun CreationFormScreen(viewModel: CreationFormVM = hiltViewModel()) {
    val listFormState by viewModel.stateOfView.collectAsState()

    LaunchedEffect(Unit) {}

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        if (listFormState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding.calculateTopPadding())
            )
        } else {
            CreationFormContent(
                state = listFormState,
                onTitleChange = { newTitle -> viewModel.onTitleChange(newTitle) },
                onDescriptionChange = { newDescription -> viewModel.onDescriptionChange(newDescription) },
            )
        }
    }
}

@Composable
fun CreationFormContent(
    state: ListCreationFormState,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
) {
    LazyColumn {
        item {
            OutlinedTextField(
                value = state.title,
                onValueChange = onTitleChange,
                label = { Text("Título del formulario") }
            )

            OutlinedTextField(
                value = state.description,
                onValueChange = onDescriptionChange,
                label = { Text("Descripción del formulario") }
            )
        }
    }
}