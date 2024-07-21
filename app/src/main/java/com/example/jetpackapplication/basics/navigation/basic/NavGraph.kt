package com.example.jetpackapplication.basics.navigation.basic

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SetUpNavGraph() {

	val navController : NavHostController = rememberNavController()
	NavHost(navController = navController , startDestination = Screens.Home.route) {
		composable(route = Screens.Home.route) {
			HomeScreen(navController)
		}
		composable(route = Screens.Details.route) {
			DetailScreen(navController)
		}

	}

}