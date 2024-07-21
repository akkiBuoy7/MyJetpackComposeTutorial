package com.example.jetpackapplication.basics.navigation.nested_navigation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
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
fun SignupScreen(navController : NavHostController) {

	Column(
		modifier = Modifier
			.fillMaxSize() ,
		verticalArrangement = Arrangement.Center ,
		horizontalAlignment =
		Alignment.CenterHorizontally
	) {
		Text(text = "Signup SCREEN" , fontWeight = FontWeight.Bold , color = Color.Black)
		Button(onClick = {
			navController.navigate(Routes.Login.route)

		}) {
			Text(text = "Go to login")
		}
		Button(onClick = {
			navController.navigate(Routes.Main.route)

		}) {
			Text(text = "Go to Main")
		}
	}

}

@Preview(showSystemUi = true)
@Composable
private fun HomePreview() {
	SignupScreen(rememberNavController())
}