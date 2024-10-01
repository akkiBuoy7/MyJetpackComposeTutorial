package com.example.jetpackapplication.basics.bottom_navigation.nav_rail.navigation_rail_nav.navigation_rail_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackapplication.basics.bottom_navigation.nav_rail.navigation_rail_screen.HomeScreenRail
import com.example.jetpackapplication.basics.bottom_navigation.nav_rail.navigation_rail_screen.ProfileScreenRail
import com.example.jetpackapplication.basics.bottom_navigation.nav_rail.navigation_rail_screen.SettingsScreenRail
import com.example.jetpackapplication.basics.bottom_navigation.nav_rail.navigation_rail_screen.rail_screens.RailScreens
import com.example.jetpackapplication.basics.nav_drawer.navigation.AllContent
import com.example.jetpackapplication.basics.nav_drawer.navigation.DrawerScreens
import com.example.jetpackapplication.basics.nav_drawer.navigation.SettingsContent
import com.example.jetpackapplication.basics.nav_drawer.navigation.UrgentContent

@Composable
fun SetupNavGraphForRail(navController : NavHostController) {
	NavHost(navController = navController , startDestination = RailScreens.Home) {

		composable<RailScreens.Home> {
			HomeScreenRail()
		}

		composable<RailScreens.Profile> {
			ProfileScreenRail()
		}

		composable<RailScreens.Settings> {
			SettingsScreenRail()
		}

	}
}