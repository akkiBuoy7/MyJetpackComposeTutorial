package com.example.jetpackapplication.basics.navigation.basic

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun SetUpNavGraph() {

	val navController : NavHostController = rememberNavController()
	NavHost(navController = navController , startDestination = Screens.Home.route) {
		composable(route = Screens.Home.route) {
			HomeScreen(navController)
		}
		composable(
			route = Screens.Details.route ,
			arguments = listOf(navArgument(DETAIL_ARG_KEY) {
				type = NavType.IntType
			} , navArgument(DETAIL_ARG_KEY_2) {
				type = NavType.StringType
			})
		) {
			// Get the arguments from navStack
			Log.d("NAVGraph" , "id: ${it.arguments?.getInt(DETAIL_ARG_KEY).toString()}")
			Log.d(
				"NAVGraph" , "name: ${
					it.arguments?.getString(DETAIL_ARG_KEY_2).toString()
				}"
			)
			DetailScreen(navController)
		}

	}

}