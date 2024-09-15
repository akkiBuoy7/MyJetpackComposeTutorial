package com.example.jetpackapplication.basics.nav_drawer.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun AllContent() {

	Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center) {
		Text(
			text = "All Screen" ,
			fontWeight = FontWeight.Bold ,
			fontStyle = FontStyle.Normal ,
			fontSize = 24.sp
		)
	}

}

@Composable
fun UrgentContent() {

	Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center) {
		Text(
			text = "Urgent Screen" ,
			fontWeight = FontWeight.Bold ,
			fontStyle = FontStyle.Normal ,
			fontSize = 24.sp
		)
	}

}

@Composable
fun SettingsContent() {

	Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center) {
		Text(
			text = "Settings Screen" ,
			fontWeight = FontWeight.Bold ,
			fontStyle = FontStyle.Normal ,
			fontSize = 24.sp
		)
	}

}

@Preview
@Composable
private fun ContentPreview() {
	SettingsContent()
}