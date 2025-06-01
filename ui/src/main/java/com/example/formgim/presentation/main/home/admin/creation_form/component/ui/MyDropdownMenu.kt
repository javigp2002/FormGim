import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropdownMenu(
    opciones: List<String>,
    onSeleccionChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Selecciona una opción",
) {
    var expanded by remember { mutableStateOf(false) }
    var seleccion by remember { mutableStateOf(opciones[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        TextField(
            value = seleccion,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                .then(modifier),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            opciones.forEach { opcion ->
                DropdownMenuItem(
                    text = { Text(opcion) },
                    onClick = {
                        seleccion = opcion
                        onSeleccionChange(opcion)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun SingleChoiceSegmentedButton(
    options: List<String>,
    onSeleccionChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var selected by remember { mutableStateOf(options[0]) }

    SingleChoiceSegmentedButtonRow(
        modifier
    ) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onClick = {
                    selected = label
                    onSeleccionChange(label)
                },
                selected = selected == label,
                label = { Text(label, maxLines = 1, style = MaterialTheme.typography.bodySmall) }
            )
        }
    }
}
