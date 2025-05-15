import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.formgim.R
import com.example.formgim.ui.theme.Constants

@Composable
fun MyShowErrorDialog(acceptOption: () -> Unit) {
    AlertDialog(
        onDismissRequest = { acceptOption() },
        title = { Text(text = stringResource(id = R.string.error_title)) },
        text = { Text(text = stringResource(id = R.string.error_message)) },
        confirmButton = {
            TextButton(onClick = { acceptOption() }) {
                Text(stringResource(id = R.string.accept_button))
            }
        }
    )
}

@Composable
fun MySubmitButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        TextButton(
            onClick = { onClick() },
            modifier = Modifier
                .padding(horizontal = Constants.PaddingSizes.L.dp)
                .fillMaxWidth(fraction = 0.5f)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    shape = MaterialTheme.shapes.medium
                ),
        ) {
            Text(
                text = stringResource(id = R.string.send_button),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}