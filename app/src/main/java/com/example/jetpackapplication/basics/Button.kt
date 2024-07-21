package com.example.jetpackapplication.basics

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyGradientButton(
	gradient : Brush ,
	text : String ,
	textColor : Color
) {

	Column(
		modifier = Modifier.fillMaxSize() , verticalArrangement = Arrangement.Center ,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Button(
			onClick = { Log.d("Button" , "MyGradientButton:CLICKED!!!") } ,
			contentPadding = PaddingValues() , // to remove excess padding bw content
			// & border
			colors = ButtonDefaults.buttonColors(
				backgroundColor = Color.Transparent // need to make transparent for
				// drawing gradient colors
			)
		) {
			Box( // to draw a gradient container
				modifier = Modifier
					.background(gradient) // colors of gradient using brush
					.padding(horizontal = 16.dp , vertical = 8.dp) ,
				contentAlignment = Alignment.Center
			) {
				Text(text = text , color = textColor)
			}

		}
	}

}