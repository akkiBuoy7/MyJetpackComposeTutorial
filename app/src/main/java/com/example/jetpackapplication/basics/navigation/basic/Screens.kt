package com.example.jetpackapplication.basics.navigation.basic

sealed class Screens(val route : String) {
	data object Home : Screens(route = "Home_screen")
	data object Details : Screens(route = "Details_screen")
}