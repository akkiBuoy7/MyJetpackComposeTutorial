package com.example.jetpackapplication.basics.splash.splash_screens

sealed class SplashScreens(val route:String){
	data object SplashScreen:SplashScreens(route = "splash")
	data object SplashDetailsScreen:SplashScreens(route = "dash")
}