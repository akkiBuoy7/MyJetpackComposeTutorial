package com.example.jetpackapplication.basics.navigation.serializable_routes.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.jetpackapplication.basics.navigation.serializable_routes.screen.Detail
import com.example.jetpackapplication.basics.navigation.serializable_routes.screen.Home
import com.example.jetpackapplication.basics.navigation.serializable_routes.screen.Screen
import kotlin.random.Random
import kotlin.random.nextInt

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
			Detail(detail.id){
			}
		}

	}

}