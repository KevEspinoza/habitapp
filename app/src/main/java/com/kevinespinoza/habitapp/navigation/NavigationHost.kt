package com.kevinespinoza.habitapp.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kevinespinoza.habitapp.authentication.presentation.login.LoginScreen
import com.kevinespinoza.habitapp.authentication.presentation.signup.SignUpScreen
import com.kevinespinoza.habitapp.home.presentation.detail.DetailScreen
import com.kevinespinoza.habitapp.home.presentation.home.HomeScreen
import com.kevinespinoza.habitapp.onboarding.presentation.OnboardingScreen
import com.kevinespinoza.habitapp.settings.presentation.SettingsScreen

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationRoute,
    logout: () -> Unit,
) {
    NavHost(navController = navHostController, startDestination = startDestination.route) {
        composable(NavigationRoute.Onboarding.route) {
            // OnBoarding
            OnboardingScreen(
                onFinish = {
                    navHostController.popBackStack()
                    navHostController.navigate(NavigationRoute.Login.route)
                }
            )
        }
        composable(NavigationRoute.Login.route) {
            LoginScreen(
                onLogin = {
                    navHostController.popBackStack()
                    navHostController.navigate(NavigationRoute.Home.route)
                },
                onSignUp = {
                    navHostController.navigate(NavigationRoute.SignUp.route)
                },
            )
        }
        composable(NavigationRoute.Home.route) {
            HomeScreen(
                onSettings = {
                    navHostController.navigate(NavigationRoute.Settings.route)
                },
                onNewHabit = {
                    navHostController.navigate(NavigationRoute.Detail.route)
                },
                onEditHabit = {
                    navHostController.navigate(NavigationRoute.Detail.route + "?habitId=$it")
                }
            )
        }
        composable(NavigationRoute.SignUp.route) {
            SignUpScreen(
                onSignIn = {
                    navHostController.navigate(NavigationRoute.Home.route) {
                        popUpTo(navHostController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                onLogIn = {
                    navHostController.popBackStack()
                },
            )
        }

        composable(
            NavigationRoute.Detail.route + "?habitId={habitId}", arguments = listOf(
                navArgument("habitId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )) {
            DetailScreen(
                onBack = {
                    navHostController.popBackStack()
                },
                onSave = {
                    navHostController.popBackStack()
                },
            )
        }
        composable(NavigationRoute.Settings.route) {
            SettingsScreen(
                onBack = {
                    navHostController.popBackStack()
                },
                onLogout = {
                    logout()
                    navHostController.navigate(NavigationRoute.Login.route) {
                        popUpTo(navHostController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}