package com.example.jetpackapplication.basics.bottom_navigation

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

	 object Home :
			BottomBarScreen(route = "home" , title = "Home" , Icons.Default.Home)

	 object Profile :
			BottomBarScreen(route = "profile" , title = "Profile" , Icons.Default.Person)

	 object Settings :
			BottomBarScreen(
				route = "settings" ,
				title = "Settings" ,
				Icons.Default.Settings
			)
}