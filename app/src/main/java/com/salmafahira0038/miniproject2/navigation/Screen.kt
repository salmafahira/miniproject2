package com.salmafahira0038.miniproject2.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object FormBaru: Screen("derailScreen")
}