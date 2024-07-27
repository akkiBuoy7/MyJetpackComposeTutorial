package com.example.jetpackapplication.basics.bottom_navigation.basic.bottom_nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

// step 1 : Describe the screens along with vars
sealed class BottomBarScreen(
	val route : String ,
	val title : String ,
	val icon : ImageVector
) {

	 data object Home :
			 BottomBarScreen(route = "home" , title = "Home" , Icons.Default.Home)

	 data object Profile :
			 BottomBarScreen(route = "profile" , title = "Profile" , Icons.Default.Person)

	 data object Settings :
			 BottomBarScreen(
				route = "settings" ,
				title = "Settings" ,
				Icons.Default.Settings
			)
}