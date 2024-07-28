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
import com.example.jetpackapplication.basics.navigation.nested_navigation.nav.DASH_GRAPH_ROUTE

@Composable
fun LoginScreen(navController : NavHostController) {

	Column(
		modifier = Modifier
			.fillMaxSize() ,
		verticalArrangement = Arrangement.Center ,
		horizontalAlignment =
		Alignment.CenterHorizontally
	) {
		Text(text = "Login SCREEN" , fontWeight = FontWeight.Bold , color = Color.Black)
		Button(onClick = {
			navController.navigate(DASH_GRAPH_ROUTE)

		}) {
			Text(text = "Go to dashboard")
		}
	}

}

@Preview(showSystemUi = true)
@Composable
private fun LoginPreview() {
	LoginScreen(rememberNavController())
}