package com.example.jetpackapplication.basics.navigation.basic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController

@Composable
fun NextScreen(navController : NavHostController) {
	Column(
		modifier = Modifier
			.fillMaxSize() ,
		verticalArrangement = Arrangement.Center ,
		horizontalAlignment =
		Alignment.CenterHorizontally
	) {
		Text(text = "NEXT SCREEN" , fontWeight = FontWeight.Bold , color = Color.Black)
		Button(onClick = {
			navController.popBackStack()
//			navController.navigate(route = Screens.Details.route) {
//				// need to fix pop method for required param navigation
			// this will not work as details screen requires params
////				popUpTo(Screens.Home.route) {
////					inclusive = true
////				}
//			}
		}) {
			Text(text = "Back")
		}
	}

}