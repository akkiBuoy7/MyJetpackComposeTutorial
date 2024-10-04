package com.example.jetpackapplication.basics.bottom_navigation.nav_rail.navigation_rail_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapplication.basics.bottom_navigation.nav_rail.navigation_rail_nav.navigation_rail_graph.SetupNavGraphForRail


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Material3BottomRailNav(
	navController : NavHostController = rememberNavController() ,
	showNavigationRail : Boolean
) {

	Scaffold(
		bottomBar = {
			if (!showNavigationRail) {
				BottomNavBar(navController)
			}
		} ,

		) {
		SetupNavGraphForRail(navController = navController)
		if (showNavigationRail) {
			NavigationSideBar(
				navController
			)
		}



	}

}

@Composable
fun BottomNavBar(navController : NavHostController) {

	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentDestination = navBackStackEntry?.destination
	val items = listOf(
		MyNavRailItems.Home ,
		MyNavRailItems.Profile ,
		MyNavRailItems.Settings ,
	)

	var selectedItemIndex by rememberSaveable {
		mutableStateOf(0)
	}
	NavigationBar {
		items.forEachIndexed { index , item ->
			NavigationBarItem(
				selected = currentDestination?.hierarchy?.any {
					it.route == item.title
				} == true ,
				onClick = {
					selectedItemIndex = index

					navController.navigate(item.route) {
						navController.graph.startDestinationRoute?.let { route ->
							popUpTo(route) {
								saveState = true
							}
						}
						launchSingleTop = true
						restoreState = true
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

@Composable
fun NavigationSideBar(
	navController : NavHostController
) {

	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentDestination = navBackStackEntry?.destination

	var selectedItemIndex by rememberSaveable {
		mutableStateOf(0)
	}

	val items = listOf(
		MyNavRailItems.Home ,
		MyNavRailItems.Profile ,
		MyNavRailItems.Settings ,
	)

	NavigationRail(
		header = {
			IconButton(onClick = { /*TODO*/ }) {
				Icon(
					imageVector = Icons.Default.Menu ,
					contentDescription = "Menu"
				)
			}
			FloatingActionButton(
				onClick = { /*TODO*/ } ,
				elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
			) {
				Icon(
					imageVector = Icons.Default.Add ,
					contentDescription = "Add"
				)
			}
		} ,
		modifier = Modifier
			.background(MaterialTheme.colorScheme.inverseOnSurface)
			.offset(x = (-1).dp)
	) {
		Column(
			modifier = Modifier.fillMaxHeight() ,
			verticalArrangement = Arrangement.spacedBy(12.dp , Alignment.Bottom)
		) {
			items.forEachIndexed { index , item ->
				NavigationRailItem(
					selected = currentDestination?.hierarchy?.any {
						it.route == item.title
					} == true ,
					onClick = {
						selectedItemIndex = index

						navController.navigate(item.route) {
							navController.graph.startDestinationRoute?.let { route ->
								popUpTo(route) {
									saveState = true
								}
							}
							launchSingleTop = true
							restoreState = true
						}
					} ,
					icon = {
						NavigationIcon(
							item = item ,
							selected = selectedItemIndex == index
						)
					} ,
					label = {
						Text(text = item.title)
					} ,
				)
			}
		}
	}
}

@Composable
fun NavigationIcon(
	item : MyNavRailItems ,
	selected : Boolean
) {
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
			imageVector = if (selected) item.selectedIcon else item.unselectedIcon ,
			contentDescription = item.title
		)
	}
}

