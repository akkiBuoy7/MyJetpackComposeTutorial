package com.example.jetpackapplication.basics.nav_drawer.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class DrawerScreens {

	@Serializable
	data object All : DrawerScreens()

	@Serializable
	data object Urgent : DrawerScreens()

	@Serializable
	data object Settings : DrawerScreens()
}