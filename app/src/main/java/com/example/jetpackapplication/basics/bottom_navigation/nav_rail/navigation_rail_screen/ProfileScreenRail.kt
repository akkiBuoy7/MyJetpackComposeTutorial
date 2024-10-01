package com.example.jetpackapplication.basics.bottom_navigation.nav_rail.navigation_rail_screen

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
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProfileScreenRail() {

	Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary) ,
	    contentAlignment
	    = Alignment
		    .Center){
		Text(text = "Profile Screen", fontSize = MaterialTheme.typography
			.headlineMedium.fontSize,
		     fontWeight = FontWeight.Bold, color = Color.White)
	}

}

@Preview
@Composable
private fun ProfilePreview() {
	ProfileScreenRail()
}