package com.example.jetpackapplication.basics.widgets.constraint_layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackapplication.R

@Composable
fun NormalCountryCard() {
	Surface(
		modifier = Modifier
			.fillMaxWidth(1.0f)
			.padding(2.dp) ,

		) {
		Row(modifier = Modifier.fillMaxWidth(1.0f)) {
			Column(
				modifier = Modifier
					.wrapContentSize()
					.fillMaxWidth(0.3f) ,
				verticalArrangement = Arrangement.Center ,
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Box(
					modifier = Modifier
						.padding(2.dp)
				) {
					val imageResId = R.drawable.india // Replace with your PNG image
					// resource ID
					val imagePainter : Painter = painterResource(id = imageResId)
					Image(painter = imagePainter , contentDescription = "Country Flag")
				}

				Text(
					text = "India" ,
					modifier = Modifier
						.padding(2.dp)
						.fillMaxWidth(1.0f) ,
					fontFamily = FontFamily.SansSerif ,
					textAlign = TextAlign.Center ,
					fontSize = 20.sp
				)

				Text(
					text = "New Delhi" ,
					fontSize = 10.sp ,
					textAlign = TextAlign.Center ,
					modifier = Modifier
						.padding(2.dp)
						.fillMaxWidth(1.0f)
				)
			}

			Column(
				modifier = Modifier
					.fillMaxWidth(0.85f)
					.wrapContentSize() ,

				verticalArrangement = Arrangement.SpaceEvenly
			) {
				Text(
					text = "Republic of India" ,
					fontSize = 20.sp ,
					textAlign = TextAlign.Center ,
					modifier = Modifier
						.padding(2.dp)
						.fillMaxWidth(1.0f)
				)

				Text(
					text = "Asia" ,
					fontSize = 15.sp ,
					textAlign = TextAlign.Center ,
					modifier = Modifier
						.padding(2.dp)
						.fillMaxWidth(1.0f)
				)

				Text(
					text = "South Asia" ,
					fontSize = 15.sp ,
					textAlign = TextAlign.Center ,
					modifier = Modifier
						.padding(2.dp)
						.fillMaxWidth(1.0f)
				)

				Row(
					modifier = Modifier
						.fillMaxWidth(1.0f)
						.padding(2.dp) ,
					horizontalArrangement = Arrangement.SpaceEvenly ,
					verticalAlignment = Alignment.CenterVertically
				) {
					Box(
						modifier = Modifier
							.padding(2.dp)
							.fillMaxWidth(0.3f)
					) {
						val imageResId = R.drawable.india // Replace with your PNG image
						// resource ID
						val imagePainter : Painter = painterResource(id = imageResId)
						Image(
							painter = imagePainter ,
							contentDescription = "Country Flag"
						)
					}

					Text(
						text = "Indian Rupee" ,
						fontSize = 15.sp ,
						textAlign = TextAlign.Center ,
						modifier = Modifier
							.padding(2.dp)
							.fillMaxWidth(0.4f)
					)

					Column(modifier = Modifier.fillMaxWidth(0.3f)) {

						Text(
							text = "+91" ,
							textAlign = TextAlign.Center ,
							modifier = Modifier
						)

						Text(
							text = ".in" ,
							textAlign = TextAlign.Center ,
							modifier = Modifier
						)
					}
				}
			}
		}
	}
}

@Preview
@Composable
private fun NormalCountryCardPrev() {
	NormalCountryCard()
}