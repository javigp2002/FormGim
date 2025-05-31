package com.example.formgim.presentation.main.home.components.form

import MyOutlinedTextField
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.formgim.ui.theme.Constants

@Composable
fun BoxQuestion(
    questionTitle: String, value: String, onTextoChange: (String) -> Unit, isError: Boolean,
    readonly: Boolean = true
) {
    Column(modifier = Modifier.padding(Constants.PaddingSizes.L.dp)) {
        Text(text = questionTitle)
        Spacer(modifier = Modifier.height(Constants.PaddingSizes.M.dp))
        MyOutlinedTextField(
            value = value,
            onValueChange = onTextoChange,
            label = { Text("Caja de texto") },
            modifier = Modifier.fillMaxWidth(),
            isError = isError,
            readonly = readonly
        )
    }
}

