package com.example.jetpackapplication.basics.nav_drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetpackapplication.basics.nav_drawer.navigation.DrawerScreens

sealed class NavDrawerItem(
	val title : String ,
	val selectedIcon : ImageVector ,
	val unselectedIcon : ImageVector ,
	val badgeCount : Int? = null ,
	val route : DrawerScreens ,
	//val routeS : String
) {

	data object All : NavDrawerItem(
		title = "All" ,
		selectedIcon = Icons.Filled.Home ,
		unselectedIcon = Icons.Outlined.Home ,
		route = DrawerScreens.All ,
		//routeS = "All"
	)

	data object Urgent : NavDrawerItem(
		title = "Urgent" ,
		selectedIcon = Icons.Filled.Info ,
		unselectedIcon = Icons.Outlined.Info ,
		badgeCount = 45 ,
		route = DrawerScreens.Urgent ,
		//routeS = "Urgent"
	)

	data object Settings : NavDrawerItem(
		title = "Settings" ,
		selectedIcon = Icons.Filled.Settings ,
		unselectedIcon = Icons.Outlined.Settings ,
		route = DrawerScreens.Settings ,
		//routeS = "Settings"
	)

}