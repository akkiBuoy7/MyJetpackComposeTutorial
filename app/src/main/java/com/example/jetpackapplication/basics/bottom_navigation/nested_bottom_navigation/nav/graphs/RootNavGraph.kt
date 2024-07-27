package com.example.jetpackapplication.basics.bottom_navigation.nested_bottom_navigation.nav.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackapplication.basics.bottom_navigation.nested_bottom_navigation.screens.bottom_nav.NestedHomeScreen

object Graph {

	const val ROOT = "root_graph"
	const val AUTH = "auth_graph"
	const val HOME = "home_graph"
	const val DETAILS = "details_graph"
}

@Composable
fun RootNavigationGraph(navController : NavHostController) {
	NavHost(
		navController = navController , route = Graph.ROOT , startDestination =
		Graph.AUTH
	) {
		authNavGraph(navController = navController)
		composable(route = Graph.HOME) {
			NestedHomeScreen()
		}
	}
}