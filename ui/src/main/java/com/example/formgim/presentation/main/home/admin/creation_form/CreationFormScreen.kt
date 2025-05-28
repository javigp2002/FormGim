package com.example.formgim.presentation.main.home.admin.creation_form

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

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
        }
    }


}


