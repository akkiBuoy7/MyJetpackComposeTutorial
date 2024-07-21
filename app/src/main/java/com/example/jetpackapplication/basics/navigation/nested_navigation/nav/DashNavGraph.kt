package com.example.jetpackapplication.basics.navigation.nested_navigation.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.jetpackapplication.basics.navigation.nested_navigation.screens.DashScreen
import com.example.jetpackapplication.basics.navigation.nested_navigation.screens.MainScreen

fun NavGraphBuilder.dashNavGraph(navController : NavHostController) {

	navigation(startDestination = Routes.Dash.route ,route = DASH_GRAPH_ROUTE,){
		composable(route = Routes.Dash.route) {
			DashScreen(navController = navController)
		}
		composable(route = Routes.Main.route) {
			MainScreen(navController = navController)
		}
	}

}

