package com.example.jetpackapplication.basics.bottom_navigation.basic.bottom_nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackapplication.basics.bottom_navigation.basic.bottom_screens.HomeScreen
import com.example.jetpackapplication.basics.bottom_navigation.basic.bottom_screens.ProfileScreen
import com.example.jetpackapplication.basics.bottom_navigation.basic.bottom_screens.SettingsScreen

// step 2 : Create a NavGraph with the screens
@Composable
fun BottomNavGraph(navController:NavHostController) {
	NavHost(navController = navController , startDestination = BottomBarScreen.Home.route) {
		composable(route = BottomBarScreen.Home.route){
			HomeScreen()
		}
		composable(route = BottomBarScreen.Profile.route){
			ProfileScreen()
		}
		composable(route = BottomBarScreen.Settings.route){
			SettingsScreen()
		}
	}
}