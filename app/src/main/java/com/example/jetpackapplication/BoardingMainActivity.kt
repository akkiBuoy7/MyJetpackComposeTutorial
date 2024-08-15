package com.example.jetpackapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapplication.basics.on_boarding.boarding_navigation.SetupNavGraphForBoarding
import com.example.jetpackapplication.basics.on_boarding.viewmodel.SplashViewModel
import com.example.jetpackapplication.ui.theme.JetpackApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BoardingMainActivity : ComponentActivity(){

	@Inject
	lateinit var splashViewModel: SplashViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		installSplashScreen().setKeepOnScreenCondition {
			!splashViewModel.isLoading.value
		}

		setContent {
			JetpackApplicationTheme {
				val screen by splashViewModel.startDestination
				val navController = rememberNavController()
				SetupNavGraphForBoarding(navController,startDestination = screen)
			}
		}
	}

}