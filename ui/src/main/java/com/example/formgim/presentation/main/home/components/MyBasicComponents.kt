import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.appgim.domain.main.home.models.HomeFormCard
import com.example.formgim.presentation.main.home.components.HomeCard
import com.example.formgim.ui.theme.Constants

@Composable
fun MyBorderBox(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Constants.PaddingSizes.M.dp)
            .border(1.dp, color = colorScheme.outline, shape = shapes.small)
    ) {
        content()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    backEvent: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
    showBackEvent: Boolean = true,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorScheme.primaryContainer,
            titleContentColor = colorScheme.onPrimaryContainer
        ),

        navigationIcon = {
            if (showBackEvent) {
                IconButton(onClick = { backEvent() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Localized description"
                    )
                }
            }
        },
        actions = actions,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ListFormsLazyColumn(
    form: List<HomeFormCard>,
    modifier: Modifier = Modifier,
    goToDetail: (formId: Int) -> Unit = {},
    nameButtonToDetail: String = "Realizar",
) {
    LazyColumn {
        items(
            form.size,
            key = { index -> form[index].id }
        ) { index ->
            HomeCard(
                homeFormCard = HomeFormCard(
                    id = form[index].id,
                    title = form[index].title,
                    author = form[index].author,
                ),
                onClick = {
                    goToDetail(form[index].id)
                },
                nameButton = nameButtonToDetail,
            )
        }
    }
}


@Composable
fun MyElevatedCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(Constants.PaddingSizes.M.dp),
        elevation = cardElevation(4.dp),
    ) {
        content()
    }
}

@Composable
fun QuestionTitleText(
    questionTitle: String,
    modifier: Modifier = Modifier,
    style: TextStyle = typography.titleLarge
) {
    Text(
        modifier = modifier,
        text = questionTitle,
        style = style,
        color = colorScheme.secondary,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun QuestionDescriptionText(
    questionDescription: String
) {
    Text(
        text = questionDescription,
        style = typography.bodyMedium,
        color = colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(top = Constants.PaddingSizes.S.dp)
    )
}

@Composable
fun QuestionWithResponses(
    title: String,
    spacerHeight: Dp = Constants.PaddingSizes.M.dp,
    responses: @Composable () -> Unit
) {
    Column(modifier = Modifier.padding(Constants.PaddingSizes.L.dp)) {
        QuestionTitleText(title)
        Spacer(modifier = Modifier.height(spacerHeight))

        Column(modifier = Modifier.padding(start = Constants.PaddingSizes.M.dp)) {
            responses()
        }
    }
}