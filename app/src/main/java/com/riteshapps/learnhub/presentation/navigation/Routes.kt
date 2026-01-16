package com.riteshapps.learnhub.presentation.navigation

sealed class Routes(
    val route: String,
    val title: String
) {

    object Login : Routes("login", "Login")
    object Register : Routes("register", "Register")
    object Home : Routes("home", "Home")
    object Settings : Routes("settings", "Settings")

    object Detail : Routes(
        route = "detail/{id}",
        title = "Detail"
    ) {
        fun createRoute(id: String): String {
            return "detail/$id"
        }
    }
}
