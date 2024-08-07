package com.example.jetpackapplication.basics.widgets

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha
import com.example.jetpackapplication.R
import com.example.jetpackapplication.ui.theme.shapeScheme

@Composable
fun MyExpandableCard(
	title : String ,
	titleSize : TextUnit = MaterialTheme.typography.headlineMedium.fontSize ,
	titleWeight : FontWeight = FontWeight.Bold ,
	desc : String ,
	descSize : TextUnit = MaterialTheme.typography.bodyMedium.fontSize ,
	descWeight : FontWeight = FontWeight.Normal ,
	descriptionMaxLines : Int = 4 ,
	//shape : Shape = Shapes.,
	padding : Dp = 12.dp
) {

	var expandedState by remember {
		mutableStateOf(false)
	}
	val rotationState by
	animateFloatAsState(targetValue = if (expandedState) 180f else 0f)

	Column(
		modifier = Modifier.fillMaxSize() , verticalArrangement = Arrangement.Center ,
		horizontalAlignment = Alignment
			.CenterHorizontally
	) {
		Card(
			shape = MaterialTheme.shapeScheme.large ,
//		     backgroundColor = Color.Magenta ,
             modifier = Modifier
	             .fillMaxWidth()
	             .padding(horizontal = 7.dp)
	             .animateContentSize(
		             animationSpec = tween(
			             durationMillis = 300 ,
			             easing = LinearOutSlowInEasing
		             ) ,
	             ) ,
             onClick = {
	             expandedState = !expandedState
             }) {
			Column(
				horizontalAlignment = Alignment.CenterHorizontally ,
				modifier = Modifier
					.fillMaxWidth()
					.padding(padding)
			) {
				Row(verticalAlignment = Alignment.CenterVertically) {
					Text(
						color = Color.White ,
						text = title ,
						fontSize = titleSize ,
						fontWeight = titleWeight ,
						modifier = Modifier.weight(6f)
					)
					IconButton(
						modifier = Modifier
							.weight(1f)
							.rotate(rotationState)
							.alpha(
								ContentAlpha
									.medium
							) ,
						onClick = { expandedState = !expandedState }) {
						Icon(
							imageVector = Icons.Default.ArrowDropDown ,
							contentDescription
							= "" ,
						)

					}
				}
				if (expandedState) {

					Text(
						color = Color.White ,
						text = desc ,
						fontSize = descSize ,
						fontWeight = descWeight ,
						maxLines = descriptionMaxLines ,
						overflow = TextOverflow.Ellipsis
					)
				}

			}
		}
	}

}

@Preview
@Composable
private fun MyPreview() {

	MyExpandableCard(title = "Title" , desc = stringResource(id = R.string.desc))
}