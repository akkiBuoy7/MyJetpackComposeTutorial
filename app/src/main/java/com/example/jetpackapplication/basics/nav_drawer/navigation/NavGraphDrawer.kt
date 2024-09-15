package com.example.jetpackapplication.basics.nav_drawer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapplication.basics.nav_drawer.NavDrawerItem
import com.example.jetpackapplication.basics.nav_drawer.SetupNavDrawer

@Composable
fun SetupNavDrawerGraph(navController : NavHostController) {
	NavHost(navController = navController , startDestination = DrawerScreens.All) {

		composable<DrawerScreens.All> {
			AllContent()
		}

		composable<DrawerScreens.Urgent> {
			UrgentContent()
		}

		composable<DrawerScreens.Settings>{
			SettingsContent()
		}

	}

//	NavHost(navController = navController , startDestination = NavDrawerItem.All.routeS) {
//
//		composable(NavDrawerItem.All.routeS) {
//			AllContent()
//		}
//
//		composable(NavDrawerItem.Urgent.routeS) {
//			UrgentContent()
//		}
//
//		composable(NavDrawerItem.Settings.routeS) {
//			SettingsContent()
//		}
//
//	}
}