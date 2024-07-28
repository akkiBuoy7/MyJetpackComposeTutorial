package com.example.jetpackapplication.basics.navigation.serializable_routes.screen

import com.example.jetpackapplication.basics.navigation.serializable_routes.model.DemoModel
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {

	@Serializable
	data object Home : Screen()

	@Serializable
	data class Detail(val id : Int) : Screen()

	@Serializable
	data class Profile(val demoModel : DemoModel) : Screen()
}