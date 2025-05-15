import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.formgim.R

@Composable
fun ShowErrorDialog(acceptOption: () -> Unit) {
    AlertDialog(
        onDismissRequest = { acceptOption },
        title = { Text(text = stringResource(id = R.string.error_title)) },
        text = { Text(text = stringResource(id = R.string.error_message)) },
        confirmButton = {
            TextButton(onClick = { acceptOption }) {
                Text(stringResource(id = R.string.accept_button))
            }
        }
    )
}