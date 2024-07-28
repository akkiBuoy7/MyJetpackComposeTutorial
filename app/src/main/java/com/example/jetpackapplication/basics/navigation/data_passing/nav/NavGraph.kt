package com.example.jetpackapplication.basics.navigation.data_passing.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapplication.basics.navigation.data_passing.screen.Detail
import com.example.jetpackapplication.basics.navigation.data_passing.screen.Home
import com.example.jetpackapplication.basics.navigation.data_passing.screen.Screen
import com.example.jetpackapplication.basics.navigation.data_passing.view_model.MyModel

@Composable
fun SetupNavGraphForDataPassing() {

	val navController = rememberNavController()

	NavHost(navController = navController , startDestination = Screen.Home.route) {

		composable(route = Screen.Home.route) {
			Home(navController)
		}

		composable(route = Screen.Detail.route) {
			val result =
				navController.previousBackStackEntry?.savedStateHandle?.get<MyModel>("myModel")
			Detail(result)
		}

	}

}