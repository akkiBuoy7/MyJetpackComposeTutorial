package com.example.jetpackapplication.basics.bottom_navigation.basic.bottom_nav

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter" ,
              "UnusedMaterial3ScaffoldPaddingParameter"
)
@Composable
fun MainScreen() {
	val navController = rememberNavController()
	Scaffold(bottomBar = {
		// step 5 : Attach BottomBar widget to scaffold
		BottomBarRow(navController = navController)

	}) {
		// step 6 : Attach BottomNavGraph to scaffold for navigation
		BottomNavGraph(navController = navController)
	}

}

// step 4 : Declare BottomBar widget and attach the BottomNavigation Items
@Composable
fun BottomBarRow(navController : NavHostController) {
	val screens = listOf(
		BottomBarScreen.Home ,
		BottomBarScreen.Profile ,
		BottomBarScreen.Settings
	)

	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentDestination = navBackStackEntry?.destination

	NavigationBar(containerColor = Color.Yellow) {
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

	NavigationBarItem(
		label = { screen.title } ,
		icon = {
			Icon(
				imageVector = screen.icon ,
				contentDescription = "ICON"
			)
		} ,
		selected = currentDestination?.hierarchy?.any {
			it.route == screen.route
		} == true , // if the route we sent == widget route is true then item is selected
		colors = NavigationBarItemDefaults.colors(
			selectedIconColor = Color.Red,
			selectedTextColor = Color.Yellow,
			unselectedIconColor = Color.Gray ,
			unselectedTextColor = Color.Black
		),
		onClick = {
			navController.navigate(screen.route) {
				popUpTo(navController.graph.findStartDestination().id)
				launchSingleTop = true
			}
		}
	)

}