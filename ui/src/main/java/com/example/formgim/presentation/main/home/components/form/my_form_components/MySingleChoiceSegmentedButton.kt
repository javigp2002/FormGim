import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier




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
