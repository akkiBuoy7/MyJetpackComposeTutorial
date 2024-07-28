package com.example.jetpackapplication.basics.navigation.serializable_routes.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.jetpackapplication.basics.navigation.serializable_routes.model.DemoModel
import com.example.jetpackapplication.basics.navigation.serializable_routes.model.customNavType
import com.example.jetpackapplication.basics.navigation.serializable_routes.screen.Detail
import com.example.jetpackapplication.basics.navigation.serializable_routes.screen.Home
import com.example.jetpackapplication.basics.navigation.serializable_routes.screen.Profile
import com.example.jetpackapplication.basics.navigation.serializable_routes.screen.Screen
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.reflect.typeOf

@Composable
fun SetupNavGraphForSerializationDataPassing() {

	val navController = rememberNavController()

	NavHost(navController = navController , startDestination = Screen.Home) {

		composable<Screen.Home> {
			Home() {
				navController.navigate(Screen.Detail(id = Random.nextInt(0..10)))
			}
		}

		composable<Screen.Detail> {
			val detail = it.toRoute<Screen.Detail>()
			Detail(detail.id) { demoModel ->
				navController.navigate(Screen.Profile(demoModel = demoModel))
			}
		}


		composable<Screen.Profile>(
			typeMap = mapOf(typeOf<DemoModel>() to customNavType)
		) {
			val profile = it.toRoute<Screen.Profile>()
			Profile(profile.demoModel)

		}

	}

}