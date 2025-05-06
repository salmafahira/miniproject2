package com.salmafahira0038.miniproject2.navigation

import com.salmafahira0038.miniproject2.ui.screen.KEY_ID_FILM

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object FormBaru: Screen("derailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_FILM}") {
        fun withId(id: Long) = "detailScreen/$id"
    }
}