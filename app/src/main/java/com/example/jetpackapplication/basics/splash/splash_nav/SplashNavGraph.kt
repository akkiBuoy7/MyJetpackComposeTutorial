package com.example.jetpackapplication.basics.splash.splash_nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapplication.basics.splash.splash_screens.AnimatedSplashScreen
import com.example.jetpackapplication.basics.splash.splash_screens.MainContent
import com.example.jetpackapplication.basics.splash.splash_screens.SplashScreens

@Composable
fun SetUpSplashNavGraph() {
	val navController : NavHostController = rememberNavController()

	NavHost(
		navController = navController , startDestination = SplashScreens
			.SplashScreen.route
	) {

		composable(route = SplashScreens.SplashScreen.route) {
			AnimatedSplashScreen(navController)

		}

		composable(route = SplashScreens.SplashDetailsScreen.route) {
			MainContent()

		}

	}
}