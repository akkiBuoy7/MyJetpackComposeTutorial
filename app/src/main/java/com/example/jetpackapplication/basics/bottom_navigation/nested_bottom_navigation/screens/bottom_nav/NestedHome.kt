package com.example.jetpackapplication.basics.bottom_navigation.nested_bottom_navigation.screens.bottom_nav

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
				text = screen.title ,
				fontSize = 10.sp
			)
		} ,
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

data class BottomNavigationItem(
	val title : String ,
	val selectedIcon : ImageVector ,
	val unselectedIcon : ImageVector ,
	val hasNews : Boolean ,
	val badgeCount : Int? = null
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Material3BottomNav(navController : NavHostController = rememberNavController()) {
	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentDestination = navBackStackEntry?.destination

	val items = listOf(
		BottomNavigationItem(
			title = "HOME" ,
			selectedIcon = Icons.Filled.Home ,
			unselectedIcon = Icons.Outlined.Home ,
			hasNews = false ,
		) ,
		BottomNavigationItem(
			title = "PROFILE" ,
			selectedIcon = Icons.Filled.Contacts ,
			unselectedIcon = Icons.Outlined.Contacts ,
			hasNews = false ,
			badgeCount = 45
		) ,
		BottomNavigationItem(
			title = "SETTINGS" ,
			selectedIcon = Icons.Filled.Settings ,
			unselectedIcon = Icons.Outlined.Settings ,
			hasNews = true ,
		) ,
	)
	var selectedItemIndex by rememberSaveable {
		mutableStateOf(0)
	}
	Scaffold(
		bottomBar = {
			NavigationBar {
				items.forEachIndexed { index , item ->
					NavigationBarItem(
						selected = currentDestination?.hierarchy?.any {
							it.route == item.title
						} == true ,
						onClick = {
							selectedItemIndex = index
							navController.navigate(item.title) {
								popUpTo(navController.graph.findStartDestination().id)
								launchSingleTop = true
							}
							// navController.navigate(item.title)
						} ,
						label = {
							Text(text = item.title)
						} ,
						alwaysShowLabel = false ,
						icon = {
							BadgedBox(
								badge = {
									if (item.badgeCount != null) {
										Badge {
											Text(text = item.badgeCount.toString())
										}
									} else if (item.hasNews) {
										Badge()
									}
								}
							) {
								Icon(
									imageVector = if ((index == selectedItemIndex)
										|| currentDestination?.hierarchy?.any {
											it.route == item.title
										} == true
									) {
										item.selectedIcon
									} else item.unselectedIcon ,
									contentDescription = item.title
								)
							}
						}
					)
				}
			}
		}
	) {

		HomeNavGraph(navController = navController)
	}

}
