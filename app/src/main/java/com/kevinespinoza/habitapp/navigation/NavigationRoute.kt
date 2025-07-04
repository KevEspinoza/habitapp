package com.kevinespinoza.habitapp.navigation

sealed class NavigationRoute(val route: String) {
    data object Onboarding: NavigationRoute("onboarding")
    data object Login: NavigationRoute("login")
    data object Home: NavigationRoute("home")
    data object SignUp: NavigationRoute("signUp")
    data object Detail: NavigationRoute("detail")
    data object Settings: NavigationRoute("settings")
}