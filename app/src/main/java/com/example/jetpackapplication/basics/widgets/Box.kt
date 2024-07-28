package com.example.jetpackapplication.basics.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyBox() {
	// acts like a container in flutter or frame layout
	// boxes will get stacked upon one another


	Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center) {
		Box(
			modifier = Modifier
				.size(70.dp)
				.background(Color.Blue) , contentAlignment =
			Alignment
				.Center
		) {
			Box(
				modifier = Modifier
					.size(50.dp)
					.background(Color.Red) ,
				contentAlignment =
				Alignment
					.Center
			) {
				Box(
					modifier = Modifier
						.size(30.dp)
						.background(Color.Yellow , shape = CircleShape)
						.verticalScroll(rememberScrollState()) ,
					contentAlignment =
					Alignment
						.Center
				) {
					Text(
						buildAnnotatedString {
							append("A\n")
							append("N\n")
							append("D\n")
							append("R\n")
							append("O\n")
							append("I\n")
							append("D\n")

						} ,
						fontSize = 30.sp ,
					)

				}

			}
		}
	}

}