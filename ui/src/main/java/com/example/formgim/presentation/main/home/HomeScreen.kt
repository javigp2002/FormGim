package com.example.formgim.presentation.main.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.formgim.ui.theme.Constants

@Composable
fun HomeScreen(goToDetail: () -> Unit = {}) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Text(
                text = "Home",
                modifier = Modifier.padding(Constants.PaddingSizes.M.dp),
                style = androidx.compose.material3.MaterialTheme.typography.titleLarge
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, innerPadding.calculateTopPadding(), 0.dp, 0.dp)
        ) {
            items(20) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Constants.PaddingSizes.M.dp),
                ) {
                    Text(
                        text = "Card $index",
                        modifier = Modifier.padding(Constants.PaddingSizes.L.dp)
                    )
                }
            }
        }
    }
}
