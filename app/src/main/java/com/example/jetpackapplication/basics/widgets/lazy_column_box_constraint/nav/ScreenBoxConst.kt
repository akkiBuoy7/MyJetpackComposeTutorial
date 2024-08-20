package com.example.jetpackapplication.basics.widgets.lazy_column_box_constraint.nav

sealed class ScreenBoxConst(val route: String) {
	object Home : ScreenBoxConst("home")
}