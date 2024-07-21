package com.example.jetpackapplication.basics.navigation.nested_navigation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun SetUpNestedNavGraph() {
	val navController : NavHostController = rememberNavController()

	NavHost(
		navController = navController , startDestination = AUTH_GRAPH_ROUTE ,
		route = ROOT_GRAPH_ROUTE
	) {

		authNavGraph(navController)
		dashNavGraph(navController)
	}
}