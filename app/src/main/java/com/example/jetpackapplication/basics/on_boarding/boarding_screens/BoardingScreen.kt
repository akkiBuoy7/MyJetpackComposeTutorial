package com.example.jetpackapplication.basics.on_boarding.boarding_screens

import com.example.jetpackapplication.basics.navigation.serializable_routes.model.DemoModel
import kotlinx.serialization.Serializable

@Serializable
sealed class BoardingScreen {

	@Serializable
	data object Welcome : BoardingScreen()

	@Serializable
	data object Home : BoardingScreen()

}