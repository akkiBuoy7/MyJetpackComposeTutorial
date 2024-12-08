package com.example.jetpackapplication.basics.item_selection.nav

sealed class ItemSelectionScreens (val route : String){
	data object DashScreen : ItemSelectionScreens(route = "DashScreen")
	data object DetailsScreen : ItemSelectionScreens(route = "DetailsScreen")
}