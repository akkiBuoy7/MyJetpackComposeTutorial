package com.example.jetpackapplication.basics.navigation.data_passing.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapplication.basics.navigation.data_passing.screen.Detail
import com.example.jetpackapplication.basics.navigation.data_passing.screen.Home
import com.example.jetpackapplication.basics.navigation.data_passing.screen.Screen
import com.example.jetpackapplication.basics.navigation.data_passing.view_model.MyModel
import com.example.jetpackapplication.basics.navigation.data_passing.view_model.SharedViewModel

@Composable
fun SetupNavGraphForDataPassing() {

	val navController = rememberNavController()
	val sharedViewModel : SharedViewModel = viewModel()

	NavHost(navController = navController , startDestination = Screen.Home.route) {

		composable(route = Screen.Home.route) {
			Home(navController,sharedViewModel)
		}

		composable(route = Screen.Detail.route) {
			val result =
				navController.previousBackStackEntry?.savedStateHandle?.get<MyModel>("myModel")
			Detail(result,sharedViewModel)
		}

	}

}