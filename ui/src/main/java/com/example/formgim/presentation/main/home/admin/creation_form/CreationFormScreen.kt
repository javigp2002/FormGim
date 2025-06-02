package com.example.formgim.presentation.main.home.admin.creation_form

import MyAlertDialog
import MyBorderBox
import MyOutlinedTextField
import MyTopAppBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.appgim.domain.main.home.models.form.TypeQuestionCreationForm
import com.example.formgim.presentation.main.home.admin.creation_form.states.ListCreationFormState
import com.example.formgim.presentation.main.home.components.form.my_form_components.CreationFormComponents
import com.example.formgim.presentation.main.home.components.form.showing_question_type.ChooseQuestionTypeComposable
import com.example.formgim.ui.theme.Constants

@Composable
fun CreationFormScreen(onBack: () -> Unit, viewModel: CreationFormVM = hiltViewModel()) {
    val listFormState by viewModel.stateOfView.collectAsState()

    LaunchedEffect(Unit) {}

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Constants.PaddingSizes.M.dp,
                vertical = Constants.PaddingSizes.L.dp
            ),
        topBar = {
            MyTopAppBar(
                title = "Creación de formulario",
                backEvent = { onBack() },
                actions = {
                    IconButton(
                        onClick = { viewModel.saveForm() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Save,
                            contentDescription = "Guardar Formulario"
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
        } else if (listFormState.showAlertDialog) {
            if (listFormState.hasBeenSavedProperly) {
                MyAlertDialog(
                    acceptOption = { onBack() },
                    title = "Éxito",
                    message = "El formulario se ha guardado correctamente."
                )
            } else {
                MyAlertDialog(
                    acceptOption = { onBack() },
                    title = "Error",
                    message = "Ha ocurrido un error al guardar el formulario. Por favor, inténtelo de nuevo."
                )
            }


        } else {
            CreationFormContent(
                state = listFormState,
                onTitleChange = { newTitle -> viewModel.onTitleChange(newTitle) },
                onDescriptionChange = { newDescription -> viewModel.onDescriptionChange(newDescription) },
                onSaveClick = { textFields, questionTitle, questionType ->
                    viewModel.onSaveClick(textFields, questionTitle, questionType)
                },
                onClickAddNew = { viewModel.onClickAddNew() },
                modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
            )
        }
    }
}

@Composable
fun CreationFormContent(
    state: ListCreationFormState,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSaveClick: (List<String>, String, TypeQuestionCreationForm) -> Unit,
    onClickAddNew: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            MyBorderBox {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Constants.PaddingSizes.M.dp)
                ) {
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

            Spacer(modifier = Modifier.padding(vertical = Constants.PaddingSizes.M.dp))

            state.listQuestion.forEachIndexed { index, question ->

                MyBorderBox {
                    ChooseQuestionTypeComposable(
                        question,
                        onAnswerChanged = { },
                        onMultipleChanged = { },
                        onSingleChanged = { },
                        onSliderChanged = { },
                        readonly = true,
                    )
                }

            }

            if (state.isAddingNewQuestion) {
                CreationFormComponents(
                    onSaveClick = onSaveClick,
                )
            } else {
                TextButton(
                    modifier = Modifier
                        .padding(vertical = Constants.PaddingSizes.M.dp),
                    onClick = { onClickAddNew() }
                ) {
                    Text("Añadir nueva pregunta")
                }
            }



        }
    }
}