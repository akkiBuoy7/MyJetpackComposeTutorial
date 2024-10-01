package com.example.jetpackapplication.basics.bottom_navigation.nav_rail.navigation_rail_screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetpackapplication.basics.bottom_navigation.nav_rail.navigation_rail_screen.rail_screens.RailScreens
import com.example.jetpackapplication.basics.nav_drawer.navigation.DrawerScreens

sealed class MyNavRailItems(
	val title : String ,
	val selectedIcon : ImageVector ,
	val unselectedIcon : ImageVector ,
	val badgeCount : Int? = null ,
	val route : RailScreens ,
	val hasNews : Boolean = false
	//val routeS : String
) {

	data object Home : MyNavRailItems(
		title = "Home" ,
		selectedIcon = Icons.Filled.Home ,
		unselectedIcon = Icons.Outlined.Home ,
		route = RailScreens.Home ,
		//routeS = "All"
	)

	data object Profile : MyNavRailItems(
		title = "Profile" ,
		selectedIcon = Icons.Filled.Info ,
		unselectedIcon = Icons.Outlined.Info ,
		badgeCount = 45 ,
		route = RailScreens.Profile ,
		//routeS = "Urgent"
	)

	data object Settings : MyNavRailItems(
		title = "Settings" ,
		selectedIcon = Icons.Filled.Settings ,
		unselectedIcon = Icons.Outlined.Settings ,
		route = RailScreens.Settings ,
		hasNews = true
		//routeS = "Settings"
	)

}