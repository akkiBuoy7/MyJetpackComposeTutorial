package com.example.jetpackapplication.basics.widgets.multiple_screen_size.nav

sealed class Screen(val route: String) {
	object MultipleScreenSizeScreen : Screen("home")
}