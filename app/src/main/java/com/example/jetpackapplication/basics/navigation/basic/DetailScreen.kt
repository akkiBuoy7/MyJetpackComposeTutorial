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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun DetailScreen(navController : NavHostController) {

	Column(
		modifier = Modifier
			.fillMaxSize() ,
		verticalArrangement = Arrangement.Center ,
		horizontalAlignment =
		Alignment.CenterHorizontally
	) {
		Text(text = "DETAIL SCREEN" , fontWeight = FontWeight.Bold , color = Color.Black)
		Button(onClick = {
			navController.navigate(
				route = Screens.Next.passNoValue(
					no = 3 , value =
					"Saha"
				)
			)
		}) {
			Text(text = "NEXT")
		}
		Button(onClick = {
			//navController.popBackStack()
			navController.navigate(route = Screens.Home.route) {
				popUpTo(Screens.Home.route) {
					inclusive = true
				}
			}
		}) {
			Text(text = "Back")
		}
	}

}

@Preview(showSystemUi = true)
@Composable
private fun DetailPreview() {
	DetailScreen(rememberNavController())
}