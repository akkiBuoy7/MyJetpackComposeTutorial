package com.example.jetpackapplication.basics.tab_screens.tab_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ProfileContent() {
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(color = Color.Red) ,
		contentAlignment = Alignment.Center
	) {
		Text(
			text = "Profile" ,
			fontSize = MaterialTheme.typography.headlineMedium.fontSize ,
			fontWeight = FontWeight.Bold
		)
	}
}