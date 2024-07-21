package com.example.jetpackapplication.basics.navigation.nested_navigation.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.jetpackapplication.basics.navigation.nested_navigation.screens.LoginScreen
import com.example.jetpackapplication.basics.navigation.nested_navigation.screens.SignupScreen

fun NavGraphBuilder.authNavGraph(navController : NavHostController) {
	navigation(startDestination = Routes.Signup.route , route = AUTH_GRAPH_ROUTE) {
		composable(route = Routes.Signup.route) {
			SignupScreen(navController = navController)
		}
		composable(route = Routes.Login.route) {
			LoginScreen(navController = navController)
		}
	}

}