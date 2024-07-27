package com.example.jetpackapplication.basics.bottom_navigation.nested_bottom_navigation.nav.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.jetpackapplication.basics.bottom_navigation.nested_bottom_navigation.nav.NestedBottomBarScreen
import com.example.jetpackapplication.basics.bottom_navigation.nested_bottom_navigation.screens.details.ScreenContent

@Composable
fun HomeNavGraph(navController : NavHostController) {
	NavHost(
		navController = navController ,
		route = Graph.HOME ,
		startDestination = NestedBottomBarScreen.Home.route
	) {
		composable(route = NestedBottomBarScreen.Home.route) {
			ScreenContent(
				name = NestedBottomBarScreen.Home.route ,
				onClick = {
					navController.navigate(Graph.DETAILS)
				}
			)
		}
		composable(route = NestedBottomBarScreen.Profile.route) {
			ScreenContent(
				name = NestedBottomBarScreen.Profile.route ,
				onClick = { }
			)
		}
		composable(route = NestedBottomBarScreen.Settings.route) {
			ScreenContent(
				name = NestedBottomBarScreen.Settings.route ,
				onClick = { }
			)
		}
		detailsNavGraph(navController = navController)
	}
}

fun NavGraphBuilder.detailsNavGraph(navController : NavHostController) {
	navigation(
		route = Graph.DETAILS ,
		startDestination = DetailsScreen.Information.route
	) {
		composable(route = DetailsScreen.Information.route) {
			ScreenContent(name = DetailsScreen.Information.route) {
				navController.navigate(DetailsScreen.Overview.route)
			}
		}
		composable(route = DetailsScreen.Overview.route) {
			ScreenContent(name = DetailsScreen.Overview.route) {
				navController.popBackStack(
					route = DetailsScreen.Information.route ,
					inclusive = false
				)
			}
		}
	}
}

sealed class DetailsScreen(val route : String) {
	data object Information : DetailsScreen(route = "INFORMATION")
	data object Overview : DetailsScreen(route = "OVERVIEW")
}