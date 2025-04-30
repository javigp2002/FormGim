package com.example.formgim.presentation.main.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.appgim.domain.main.home.models.HomeFormCard
import com.example.formgim.presentation.main.home.components.HomeCard
import com.example.formgim.ui.theme.Constants

@Composable
fun HomeScreen(
    goToDetail: () -> Unit = {},
    homeScreenViewmodel: HomeScreenViewmodel = hiltViewModel()
) {
    val homeListState by homeScreenViewmodel.listFormsState.collectAsState()

    LaunchedEffect(Unit) {
        homeScreenViewmodel.updateListState()
    }

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
            items(homeListState.forms.size) { index ->
                HomeCard(
                    homeFormCard = HomeFormCard(
                        id = homeListState.forms[index].id,
                        title = homeListState.forms[index].title,
                        author = homeListState.forms[index].author,
                    ),
                    onClick = {
                        goToDetail()
                    }
                )
            }
        }
    }
}
