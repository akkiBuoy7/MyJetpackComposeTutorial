package com.example.jetpackapplication.basics.item_selection.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapplication.basics.item_selection.ui.ItemSelectionDashScreenContent
import com.example.jetpackapplication.basics.item_selection.ui.ItemSelectionDetailsScreenContent

@Composable
fun SetupItemSelectionNavGraph() {
	val navController : NavHostController = rememberNavController()

	NavHost(navController = navController , startDestination = ItemSelectionScreens
		.DashScreen.route) {
		composable(route = ItemSelectionScreens.DashScreen.route){
			ItemSelectionDashScreenContent()
		}
		composable(route = ItemSelectionScreens.DetailsScreen.route){
			ItemSelectionDetailsScreenContent()
		}
	}
}
