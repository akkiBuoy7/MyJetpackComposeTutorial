package com.example.jetpackapplication.basics.widgets

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun LoadingAnimationContent() {
	Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
		LoadingAnimation()
	}
}

@Composable
fun LoadingAnimation(
	modifier : Modifier = Modifier ,
	circleSize : Dp = 20.dp ,
	circleColor : Color = MaterialTheme.colorScheme.primary ,
	spaceBetween : Dp = 10.dp ,
	travelDistance : Dp = 20.dp
) {
    // 3 Animatable objects to give us float values for animation
	val circles = listOf(remember {
		Animatable(0f)
	} , remember {
		Animatable(0f)
	} , remember {
		Animatable(0f)
	})

	circles.forEachIndexed { index , animatable ->
		LaunchedEffect(key1 = animatable) {
			delay(index * 100L)
			animatable.animateTo(
				targetValue = 1f ,
				animationSpec = infiniteRepeatable(
					// allows to animate at each frame like 300,600, . . .
					animation = keyframes {
						durationMillis = 1200
						0.0f at 0 using LinearOutSlowInEasing // start at 0.0f
						1.0f at 300 using LinearOutSlowInEasing // reach at 1.0f
						0.0f at 600 using LinearOutSlowInEasing // come back at 0.0f
						0.0f at 1200 using LinearOutSlowInEasing // be at 0.0f for restart
					} ,
					repeatMode = RepeatMode.Restart
				)
			)
		}
	}
	// get the float value from Animatable circles
	val circleValues : List<Float> = circles.map { it.value }
	// vertical distance to travel
	val distance = with(LocalDensity.current) {
		travelDistance.toPx()
	}
	val lastCircle = circleValues.size - 1

	Row(modifier = modifier) {

		circleValues.forEachIndexed { index , value ->
			Box(modifier = Modifier
				.size(circleSize)
				.graphicsLayer {
					// will travel based on 0.0f or 1.0f value
					translationY = -value * distance
				}
				.background(shape = CircleShape , color = circleColor))

			if (index != lastCircle) {
				Spacer(modifier = Modifier.width(spaceBetween))
			}
		}

	}

}