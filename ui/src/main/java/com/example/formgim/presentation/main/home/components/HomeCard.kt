package com.example.formgim.presentation.main.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.formgim.presentation.main.home.components.models.HomeFormCard
import com.example.formgim.ui.theme.Constants


@Composable
fun HomeCard(
    homeFormCard: HomeFormCard
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Constants.PaddingSizes.M.dp)
            .clickable { homeFormCard.onClick },
        elevation = CardDefaults.cardElevation(Constants.Dimensions.CARD_ELEVATION.dp)
    ) {
        Column(
            modifier = Modifier.padding(Constants.PaddingSizes.L.dp)
        ) {
            Text(text = homeFormCard.title, style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(Constants.PaddingSizes.S.dp))
            Text(text = homeFormCard.author, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(Constants.PaddingSizes.M.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = homeFormCard.onClick,
                    modifier = Modifier.border(
                        width = Constants.Dimensions.BUTTON_BORDER.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = MaterialTheme.shapes.small
                    )
                ) {
                    Text(text = "Realizar", color = MaterialTheme.colorScheme.primary)
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeCardPreview() {
    HomeCard(
        homeFormCard = HomeFormCard(
            id = 1,
            title = "Card Title",
            author = "Author Name",
            onClick = {}
        )
    )
}