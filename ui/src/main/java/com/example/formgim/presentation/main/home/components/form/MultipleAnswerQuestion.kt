package com.example.formgim.presentation.main.home.components.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.formgim.ui.theme.Constants

@Composable
fun MultipleOptionQuestion(
    questionTitle: String,
    options: List<String>,
    selected: Set<Int>,
    onSeleccionChange: (Set<Int>) -> Unit
) {
    Column(modifier = Modifier.padding(Constants.PaddingSizes.L.dp)) {
        Text(text = questionTitle)
        options.forEachIndexed { index, opcion ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = selected.contains(index),
                    onCheckedChange = {
                        val nuevaSeleccion = selected.toMutableSet()
                        if (it) nuevaSeleccion.add(index) else nuevaSeleccion.remove(index)
                        onSeleccionChange(nuevaSeleccion)
                    }
                )
                Text(text = opcion)
            }
        }
    }
}

