package com.example.jetpackapplication.basics.on_boarding.boarding_screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BoardingHome() {
	Box(
		modifier = Modifier.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		Text(
			text = "HOME",
			fontSize = MaterialTheme.typography.headlineMedium.fontSize
		)
	}
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
	BoardingHome()
}

