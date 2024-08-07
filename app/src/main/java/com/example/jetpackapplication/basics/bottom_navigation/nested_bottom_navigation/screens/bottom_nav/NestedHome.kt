package com.example.jetpackapplication.basics.bottom_navigation.nested_bottom_navigation.screens.bottom_nav

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapplication.basics.bottom_navigation.nested_bottom_navigation.nav.NestedBottomBarScreen
import com.example.jetpackapplication.basics.bottom_navigation.nested_bottom_navigation.nav.graphs.HomeNavGraph

@SuppressLint(
	"UnusedMaterialScaffoldPaddingParameter" ,
	"UnusedMaterial3ScaffoldPaddingParameter"
)
@Composable
fun NestedHomeScreen(navController : NavHostController = rememberNavController()) {
	Scaffold(
		bottomBar = { BottomBar(navController = navController) }
	) {
		HomeNavGraph(navController = navController)
	}
}

@Composable
fun BottomBar(navController : NavHostController) {
	val screens = listOf(
		NestedBottomBarScreen.Home ,
		NestedBottomBarScreen.Profile ,
		NestedBottomBarScreen.Settings ,
	)
	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentDestination = navBackStackEntry?.destination

	val bottomBarDestination = screens.any { it.route == currentDestination?.route }
	if (bottomBarDestination) {
		NavigationBar {
			screens.forEach { screen ->
				AddItem(
					screen = screen ,
					currentDestination = currentDestination ,
					navController = navController
				)
			}
		}
	}
}

@Composable
fun RowScope.AddItem(
	screen : NestedBottomBarScreen ,
	currentDestination : NavDestination? ,
	navController : NavHostController
) {
	NavigationBarItem(
		label = {
			Text(
				text = screen.title,
				fontSize = 10.sp
			)
		},
		icon = {
			Icon(
				imageVector = screen.icon ,
				contentDescription = "Navigation Icon"
			)
		} ,
		selected = currentDestination?.hierarchy?.any {
			it.route == screen.route
		} == true ,
		colors = NavigationBarItemDefaults.colors(
			selectedIconColor = Color.Red ,
			selectedTextColor = Color.Yellow ,
			unselectedIconColor = Color.Gray ,
			unselectedTextColor = Color.White
		) ,
		onClick = {
			navController.navigate(screen.route) {
				popUpTo(navController.graph.findStartDestination().id)
				launchSingleTop = true
			}
		}
	)
}