package com.example.formgim.presentation.main.home.navigation

import com.example.formgim.presentation.main.navigation.MainNavigationScreenNames


val HOME_SCREEN_ROUTE = MainNavigationScreenNames.Detail.name

enum class HomeTab(
    val label: String,
    val route: String
) {
    NewForms("New Forms", "${HOME_SCREEN_ROUTE}/new_forms"),
    DoneForms("Done Forms", "${HOME_SCREEN_ROUTE}/done_forms"),
    MyForms("My Forms", "${HOME_SCREEN_ROUTE}/my_forms"),
}
