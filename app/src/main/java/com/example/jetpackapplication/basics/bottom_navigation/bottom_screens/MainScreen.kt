package com.example.jetpackapplication.basics.bottom_navigation.bottom_screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapplication.basics.bottom_navigation.BottomBarScreen
import com.example.jetpackapplication.basics.bottom_navigation.BottomNavGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
	val navController = rememberNavController()
	Scaffold(bottomBar = {
		// step 5 : Attach BottomBar widget to scaffold
		BottomBar(navController = navController)

	}) {
		// step 6 : Attach BottomNavGraph to scaffold for navigation
		BottomNavGraph(navController = navController)
	}

}

// step 4 : Declare BottomBar widget and attach the BottomNavigation Items
@Composable
fun BottomBar(navController : NavHostController) {
	val screens = listOf(
		BottomBarScreen.Home ,
		BottomBarScreen.Profile ,
		BottomBarScreen.Settings
	)

	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentDestination = navBackStackEntry?.destination

	BottomNavigation {
		screens.forEach { screen ->
			AddItem(
				screen = screen , currentDestination = currentDestination ,
				navController = navController
			)
		}
	}
}

// step 3 : Create design for BottomNavigation items
@Composable
fun RowScope.AddItem(
	screen : BottomBarScreen , currentDestination : NavDestination? ,
	navController
	: NavHostController
) {

	BottomNavigationItem(
		label = { screen.title } ,
		icon = {
			Icon(
				imageVector = screen.icon ,
				contentDescription = "ICON"
			)
		} ,
		selected = currentDestination?.hierarchy?.any {
			it.route == screen.route
		} == true ,
		unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled) ,
		onClick = {
			navController.navigate(screen.route) {
				popUpTo(navController.graph.findStartDestination().id)
				launchSingleTop = true
			}
		}
	)

}