package com.example.jetpackapplication.basics.bottom_navigation.nested_bottom_navigation.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NestedBottomBarScreen(
	val route : String ,
	val title : String ,
	val icon : ImageVector
) {

	data object Home : NestedBottomBarScreen(
		route = "HOME" ,
		title = "HOME" ,
		icon = Icons.Default.Home
	)

	data object Profile : NestedBottomBarScreen(
		route = "PROFILE" ,
		title = "PROFILE" ,
		icon = Icons.Default.Person
	)

	data object Settings : NestedBottomBarScreen(
		route = "SETTINGS" ,
		title = "SETTINGS" ,
		icon = Icons.Default.Settings
	)
}