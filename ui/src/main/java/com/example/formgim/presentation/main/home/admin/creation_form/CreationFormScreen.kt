package com.example.formgim.presentation.main.home.admin.creation_form

import MyOutlinedTextField
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.formgim.presentation.main.home.admin.creation_form.states.ListCreationFormState
import com.example.formgim.ui.theme.Constants

@Composable
fun CreationFormScreen(viewModel: CreationFormVM = hiltViewModel()) {
    val listFormState by viewModel.stateOfView.collectAsState()

    LaunchedEffect(Unit) {}

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Constants.PaddingSizes.M.dp,
                vertical = Constants.PaddingSizes.L.dp
            )
    ) { innerPadding ->
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
            MyOutlinedTextField(
                value = state.title,
                onValueChange = onTitleChange,
                label = { Text("Título del formulario") }
            )

            MyOutlinedTextField(
                value = state.description,
                onValueChange = onDescriptionChange,
                label = { Text("Descripción del formulario") }
            )
        }
    }
}