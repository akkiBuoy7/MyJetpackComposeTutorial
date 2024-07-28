package com.example.jetpackapplication.basics.navigation.nested_navigation.screens

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
import com.example.jetpackapplication.basics.navigation.nested_navigation.nav.Routes

@Composable
fun DashScreen(navController : NavHostController) {

	Column(
		modifier = Modifier
			.fillMaxSize() ,
		verticalArrangement = Arrangement.Center ,
		horizontalAlignment =
		Alignment.CenterHorizontally
	) {
		Text(text = "Dash SCREEN" , fontWeight = FontWeight.Bold , color = Color.Black)
		Button(onClick = {
			navController.navigate(Routes.Main.route)
		}) {
			Text(text = "Go to main")
		}
		Button(onClick = {
			// for going to second screen of the other nav graph
			navController.navigate(Routes.Login.route)

		}) {
			Text(text = "Go to login")
		}
	}

}

@Preview(showSystemUi = true)
@Composable
private fun DashPreview() {
	DashScreen(rememberNavController())
}