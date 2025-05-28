package com.example.formgim.presentation.main.home.admin.creation_form.component.ui

import MyOutlinedTextField
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.formgim.ui.theme.Constants


@Composable
fun DynamicOutlinedTextFields(
    addTextField: () -> Unit,
    removeTextField: () -> Unit,
    textFields: MutableList<String>,
    modifier: Modifier = Modifier,
    minTextFields: Int = 2
) {
    Box(
        modifier = modifier.border(
            1.dp, color = colorScheme.outline,
            shape = shapes.small
        )
    ) {
        Column(
            modifier = Modifier.padding(
                vertical = Constants.PaddingSizes.M.dp,
                horizontal = Constants.PaddingSizes.L.dp
            )
        ) {
            textFields.forEachIndexed { index, text ->
                MyOutlinedTextField(
                    value = text,
                    onValueChange = { newValue -> textFields[index] = newValue },
                    label = { Text("Opción ${index + 1}") },
                    modifier = Modifier.padding(top = Constants.PaddingSizes.S.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                if (textFields.size > minTextFields) {
                    IconButton(
                        onClick = { removeTextField() },
                        modifier = Modifier.padding(top = Constants.PaddingSizes.S.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            tint = colorScheme.error,
                            contentDescription = "Agregar campo"
                        )
                    }
                }
                IconButton(
                    onClick = { addTextField() },
                    modifier = Modifier.padding(top = Constants.PaddingSizes.S.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Agregar campo"
                    )
                }
            }
        }
    }
}

@Composable
fun CreationFormComponents(
    modifier: Modifier = Modifier,
    onSaveClick: (textFields: List<String>, questionTitle: String) -> Unit
) {
    val textFields = remember { mutableStateListOf("", "") }
    var questionTitle by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, color = colorScheme.outline, shape = shapes.small)
    ) {


        Column(
            modifier = modifier
                .padding(Constants.PaddingSizes.L.dp)
        ) {
            MyOutlinedTextField(
                value = questionTitle,
                onValueChange = { newTitle -> questionTitle = newTitle },
                label = { Text("Título Pregunta") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(top = Constants.PaddingSizes.M.dp))

            DynamicOutlinedTextFields(
                addTextField = { textFields.add("") },
                removeTextField = { textFields.removeAt(textFields.lastIndex) },
                textFields = textFields,
            )

            Spacer(modifier = Modifier.padding(top = Constants.PaddingSizes.M.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(
                    onClick = { onSaveClick(textFields.toList(), questionTitle) },
                    modifier = Modifier.padding(top = Constants.PaddingSizes.S.dp)
                ) {
                    Text(
                        "Guardar Pregunta"
                    )
                }
            }
        }
    }


}



