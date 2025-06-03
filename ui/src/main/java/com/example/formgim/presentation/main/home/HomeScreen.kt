package com.example.formgim.presentation.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.formgim.presentation.main.home.navigation.HomeTab
import com.example.formgim.presentation.main.home.navigation.HomeTabHost
import com.example.formgim.ui.theme.Constants


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    goToDetail: (formId: Int) -> Unit = {},
    homeScreenViewmodel: HomeScreenViewmodel = hiltViewModel(),
    goToCreationForm: () -> Unit = {},
    goToDoneForm: (formId: Int) -> Unit = {},
    goToFormStats: (formId: Int) -> Unit = {},

) {
    val homeListState by homeScreenViewmodel.listFormsState.collectAsState()
    val navController = rememberNavController()
    val startDestination = HomeTab.NewForms
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    LaunchedEffect(Unit) {
        homeScreenViewmodel.updateListState()
        homeScreenViewmodel.getUserIsAdmin()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Text(
                text = "Home",
                modifier = Modifier.padding(Constants.PaddingSizes.M.dp),
                style = typography.displaySmall
            )
        },
        floatingActionButton = {
            if (homeListState.isAdmin) {
                FloatingActionButton(
                    onClick = { goToCreationForm() },
                    modifier = Modifier.padding(Constants.PaddingSizes.M.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Form"
                    )
                }
            }
        }
    ) { contentPadding ->
        if (homeListState.isLoading) {
            // Show loading indicator
            Text(
                text = "Loading...",
                modifier = Modifier.padding(contentPadding),
                style = typography.bodyLarge
            )
            return@Scaffold
        }
        Column {
            PrimaryTabRow(selectedTabIndex = selectedDestination, modifier = Modifier.padding(contentPadding)) {
                HomeTab.entries.forEachIndexed { index, destination ->
                    Tab(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = index
                        },
                        text = {
                            Text(
                                text = destination.label,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }
            }
            HomeTabHost(
                navController, startDestination, homeListState.isAdmin,
                goToDetail = goToDetail,
                goToDoneForm = goToDoneForm,
                goToFormStats = goToFormStats
            )
        }

    }

}
