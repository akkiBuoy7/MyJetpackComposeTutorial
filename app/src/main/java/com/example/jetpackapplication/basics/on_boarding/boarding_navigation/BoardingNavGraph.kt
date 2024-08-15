package com.example.jetpackapplication.basics.on_boarding.boarding_navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapplication.basics.on_boarding.boarding_screens.BoardingHome
import com.example.jetpackapplication.basics.on_boarding.boarding_screens.BoardingScreen
import com.example.jetpackapplication.basics.on_boarding.boarding_screens.Welcome
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalAnimationApi::class , ExperimentalPagerApi::class)
@Composable
fun SetupNavGraphForBoarding(navController: NavHostController, startDestination : Any) {

	NavHost(navController = navController , startDestination = startDestination) {

		composable<BoardingScreen.Welcome> {
			Welcome(onClick = {
				navController.popBackStack()
				navController.navigate(BoardingScreen.Home)
			})
		}

		composable<BoardingScreen.Home> {
			BoardingHome()
		}

	}

}