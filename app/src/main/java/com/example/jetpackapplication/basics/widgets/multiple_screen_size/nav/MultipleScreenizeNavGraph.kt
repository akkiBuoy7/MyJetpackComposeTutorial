package com.example.jetpackapplication.basics.widgets.multiple_screen_size.nav

import MultipleScreenSizeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapplication.basics.widgets.multiple_screen_size.util.WindowSize
import com.example.jetpackapplication.basics.widgets.multiple_screen_size.util.rememberWindowSize

@Composable
fun SetupNavGraphForMultipleScreenSize() {
	val window = rememberWindowSize()
	val navController = rememberNavController()
	NavHost(
		navController = navController ,
		startDestination = Screen.MultipleScreenSizeScreen.route
	) {
		composable(route = Screen.MultipleScreenSizeScreen.route) {
			MultipleScreenSizeScreen(windowSize = window)
		}
	}
}