package com.example.formgim.presentation.main.home.admin.creation_form.component.ui

import MyDropdownMenu
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.appgim.domain.main.home.models.form.TypeQuestionCreationForm
import com.example.formgim.R
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
                    label = { Text(stringResource(R.string.opcion) + " ${index + 1}") },
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
                            contentDescription = "${R.string.agregar_campo}"
                        )
                    }
                }
                IconButton(
                    onClick = { addTextField() },
                    modifier = Modifier.padding(top = Constants.PaddingSizes.S.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "${R.string.agregar_campo}"
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreationFormComponents(
    modifier: Modifier = Modifier,
    onSaveClick: (textFields: List<String>, questionTitle: String, TypeQuestionCreationForm) -> Unit
) {
    val textFields = remember { mutableStateListOf("", "") }
    var questionTitle by remember { mutableStateOf("") }
    var questionType by remember { mutableStateOf(TypeQuestionCreationForm.TEXTBOX) }
    var error by remember { mutableStateOf("") }

    fun changeType(newType: TypeQuestionCreationForm) {
        if (questionType == newType) return
        questionType = newType
    }


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
                label = { Text(stringResource(R.string.titulo_pregunta)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(top = Constants.PaddingSizes.M.dp))



            MyDropdownMenu(
                opciones = TypeQuestionCreationForm.entries.map { it.name },
                onSeleccionChange = { selectedOption ->
                    changeType(TypeQuestionCreationForm.valueOf(selectedOption))
                },
                modifier = Modifier
                    .fillMaxWidth(),
                label = stringResource(R.string.tipo_pregunta)
            )


            Spacer(modifier = Modifier.padding(top = Constants.PaddingSizes.M.dp))

            if (questionType == TypeQuestionCreationForm.CHECKBOX || questionType == TypeQuestionCreationForm.RADIOBOX) {
                DynamicOutlinedTextFields(
                    addTextField = { textFields.add("") },
                    removeTextField = { textFields.removeAt(textFields.lastIndex) },
                    textFields = textFields,

                    )

                Spacer(modifier = Modifier.padding(top = Constants.PaddingSizes.M.dp))
            }


            if (error.isNotEmpty()) {
                Text(
                    text = stringResource(error.toInt()),
                    color = colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = Constants.PaddingSizes.S.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(
                    onClick = {
                        if (questionTitle.isEmpty()) {
                            error = (R.string.error_titulo_pregunta.toString())
                            return@TextButton
                        } else if (textFields.any { it.isEmpty() } && questionType != TypeQuestionCreationForm
                                .TEXTBOX && questionType != TypeQuestionCreationForm.SLIDER) {
                            error = (R.string.error_opciones_pregunta.toString())
                            return@TextButton
                        } else {
                            error = ""
                        }
                        onSaveClick(textFields.toList(), questionTitle, questionType)
                    },
                    modifier = Modifier.padding(top = Constants.PaddingSizes.S.dp)
                ) {
                    Text(
                        stringResource(R.string.guardar_pregunta),
                    )
                }
            }
        }
    }
}