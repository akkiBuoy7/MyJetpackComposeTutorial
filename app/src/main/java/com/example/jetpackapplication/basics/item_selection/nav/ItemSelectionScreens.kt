package com.example.jetpackapplication.basics.item_selection.nav

import com.example.jetpackapplication.basics.item_selection.data.DemoItemSelection
import kotlinx.serialization.Serializable

@Serializable
sealed class ItemSelectionScreens (val route : String){
	@Serializable
	data object DashScreen : ItemSelectionScreens(route = "DashScreen")
	@Serializable
	data class DetailsScreen(val itemSelection : List<DemoItemSelection>) :
			ItemSelectionScreens(route = "DetailsScreen")
}