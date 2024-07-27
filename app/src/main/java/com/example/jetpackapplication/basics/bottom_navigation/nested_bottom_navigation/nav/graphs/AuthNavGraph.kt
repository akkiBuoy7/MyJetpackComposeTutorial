package com.example.jetpackapplication.basics.bottom_navigation.nested_bottom_navigation.nav.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.jetpackapplication.basics.bottom_navigation.nested_bottom_navigation.screens.auth.LoginContent
import com.example.jetpackapplication.basics.bottom_navigation.nested_bottom_navigation.screens.details.ScreenContent

fun NavGraphBuilder.authNavGraph(navController : NavHostController) {
	navigation(
		route = Graph.AUTH ,
		startDestination = AuthScreen.Login.route
	) {
		composable(route = AuthScreen.Login.route) {
			LoginContent(
				onClick = {
					navController.popBackStack()
					navController.navigate(Graph.HOME)
				} ,
				onSignUpClick = {
					navController.navigate(AuthScreen.SignUp.route)
				} ,
				onForgotClick = {
					navController.navigate(AuthScreen.Forgot.route)
				}
			)
		}
		composable(route = AuthScreen.SignUp.route) {
			ScreenContent(name = AuthScreen.SignUp.route) {}
		}
		composable(route = AuthScreen.Forgot.route) {
			ScreenContent(name = AuthScreen.Forgot.route) {}
		}
	}
}

sealed class AuthScreen(val route : String) {
	data object Login : AuthScreen(route = "LOGIN")
	data object SignUp : AuthScreen(route = "SIGN_UP")
	data object Forgot : AuthScreen(route = "FORGOT")
}