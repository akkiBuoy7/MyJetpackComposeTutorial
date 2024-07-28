package com.example.jetpackapplication.basics.bottom_navigation.nested_bottom_navigation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ForgotScreen() {

	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(MaterialTheme.colors.primary) ,
		contentAlignment
		= Alignment
			.Center
	) {
		Text(
			text = "Forgot Screen" , fontSize = MaterialTheme.typography.h4.fontSize ,
			fontWeight = FontWeight.Bold , color = Color.White
		)
	}

}

@Preview
@Composable
private fun ForgotPreview() {
	ForgotScreen()
}