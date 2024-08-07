package com.example.jetpackapplication.basics.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackapplication.R

@Composable
fun MyImage() {
	Column(
		modifier = Modifier.fillMaxSize() , verticalArrangement = Arrangement.SpaceEvenly ,
		horizontalAlignment = Alignment.CenterHorizontally
	) {

		Image(modifier = Modifier.size(200.dp),
			painter = painterResource(id = R.drawable.nature) , contentDescription
			= "normal"
		)


		Image(
			modifier = Modifier.size(200.dp).clip(CircleShape) ,
			painter = painterResource(id = R.drawable.nature) , contentDescription
			= "circle",contentScale = ContentScale.Crop
		)

		Image(
			modifier = Modifier.size(200.dp).clip(RoundedCornerShape(size = 80.dp)).border(
				width = 10.dp,
				color = Color.Cyan,
				shape = RoundedCornerShape(size = 80.dp)
			) ,
			painter = painterResource(id = R.drawable.nature) , contentDescription
			= "RoundedCornerShape", contentScale = ContentScale.Crop
		)

		Image(
			modifier = Modifier.size(200.dp).clip(CircleShape).border(
				width = 10.dp,
				color = Color.Cyan,
				shape = CircleShape
			) ,
			painter = painterResource(id = R.drawable.nature) , contentDescription
			= "CircleShape with border", contentScale = ContentScale.Crop
		)

	}
}

@Preview
@Composable
private fun MyImagePrev() {
	MyImage()

}