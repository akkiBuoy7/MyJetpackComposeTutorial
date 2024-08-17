package com.example.jetpackapplication.basics.widgets

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun SelectableItemContent() {

	var selected by remember {
		mutableStateOf(false)
	}

	Column(
		modifier = Modifier.fillMaxSize() ,
		verticalArrangement = Arrangement.Center ,
		horizontalAlignment = Alignment.CenterHorizontally
	) {

		SelectableItem(
			title = "Lorem Ipsum is simply dummy text of the printing " +
					"and typesetting industry." ,
			subtitle = " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum." ,
			selected = selected
		) {title ->
			selected = !selected
			Log.d("SELECTED TITLE" , "SelectableItemContent: $title")
		}
	}

}

@Composable
fun SelectableItem(
	modifier : Modifier = Modifier ,
	selected : Boolean ,
	title : String ,
	titleColor : Color =
		if (selected) MaterialTheme.colors.primary
		else MaterialTheme.colors.onSurface.copy(alpha = 0.2f) ,
	titleSize : TextUnit = MaterialTheme.typography.h6.fontSize ,
	titleWeight : FontWeight = FontWeight.Medium ,
	subtitle : String? = null ,
	subtitleColor : Color =
		if (selected) MaterialTheme.colors.onSurface
		else MaterialTheme.colors.onSurface.copy(alpha = 0.2f) ,
	borderWidth : Dp = 1.dp ,
	borderColor : Color =
		if (selected) MaterialTheme.colors.primary
		else MaterialTheme.colors.onSurface.copy(alpha = 0.2f) ,
	borderShape : Shape = RoundedCornerShape(size = 10.dp) ,
	icon : ImageVector = Icons.Default.CheckCircle ,
	iconColor : Color =
		if (selected) MaterialTheme.colors.primary
		else MaterialTheme.colors.onSurface.copy(alpha = 0.2f) ,
	onClick : (text:String) -> Unit
) {
	val scaleA = remember { Animatable(initialValue = 1f) }
	val scaleB = remember { Animatable(initialValue = 1f) }

	val clickEnabled = remember { mutableStateOf(true) }
    // execute the code of LaunchedEffect everytime selected value changes
	LaunchedEffect(key1 = selected) {
		if (selected) {
			// make it false before animation
			clickEnabled.value = false
			// A for Icon animation
			val jobA = launch {
				scaleA.animateTo(
					targetValue = 0.3f ,
					animationSpec = tween(
						durationMillis = 50
					)
				)
				scaleA.animateTo(
					targetValue = 1f ,
					animationSpec = spring(
						dampingRatio = Spring.DampingRatioLowBouncy ,
						stiffness = Spring.StiffnessLow
					)
				)
			}

			// B for column animation
			val jobB = launch {
				scaleB.animateTo(
					targetValue = 0.9f ,
					animationSpec = tween(
						durationMillis = 50
					)
				)
				scaleB.animateTo(
					targetValue = 1f ,
					animationSpec = spring(
						dampingRatio = Spring.DampingRatioLowBouncy ,
						stiffness = Spring.StiffnessLow
					)
				)
			}

			jobA.join()
			jobB.join()
			// make it true after animation
			clickEnabled.value = true
		}
	}

	Column(
		modifier = modifier
			.scale(scale = scaleB.value)
			.border(
				width = borderWidth ,
				color = borderColor ,
				shape = borderShape
			)
			.clip(borderShape)
			.clickable(enabled = clickEnabled.value) {

				Log.d("MY CLICK ENABLED" , "SelectableItem: ${clickEnabled.value}")
				onClick(title)
			}
	) {
		Row(
			modifier = Modifier.padding(start = 12.dp) ,
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				modifier = Modifier.weight(8f) ,
				text = title ,
				style = TextStyle(
					color = titleColor ,
					fontSize = titleSize ,
					fontWeight = titleWeight
				) ,
				maxLines = 1 ,
				overflow = TextOverflow.Ellipsis
			)
			IconButton(
				modifier = Modifier
					.weight(2f)
					.scale(scale = scaleA.value) ,
				onClick = {
					if (clickEnabled.value) {
						onClick(title)
					}
				}
			) {
				Icon(
					imageVector = icon ,
					contentDescription = "Selectable Item Icon" ,
					tint = iconColor
				)
			}
		}
		if (subtitle != null) {
			Text(
				modifier = Modifier
					.padding(horizontal = 12.dp)
					.padding(bottom = 12.dp) ,
				text = subtitle ,
				style = TextStyle(
					color = subtitleColor
				) ,
				maxLines = 3 ,
				overflow = TextOverflow.Ellipsis
			)
		}
	}
}