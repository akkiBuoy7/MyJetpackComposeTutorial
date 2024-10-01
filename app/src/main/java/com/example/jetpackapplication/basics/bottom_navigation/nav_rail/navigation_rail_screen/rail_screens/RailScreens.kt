package com.example.jetpackapplication.basics.bottom_navigation.nav_rail.navigation_rail_screen.rail_screens

import kotlinx.serialization.Serializable

@Serializable
sealed class RailScreens {

	@Serializable
	data object Home : RailScreens()

	@Serializable
	data object Profile : RailScreens()

	@Serializable
	data object Settings : RailScreens()
}