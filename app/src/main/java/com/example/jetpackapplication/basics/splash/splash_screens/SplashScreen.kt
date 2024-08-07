package com.example.jetpackapplication.basics.splash.splash_screens

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.Icon
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController : NavController) {

	var isStartAnim by remember {
		mutableStateOf(false)
	}

	val alphaAnim = animateFloatAsState(
		targetValue = if (isStartAnim) 1f else 0f ,
		animationSpec = tween(durationMillis = 3000)
	)

	LaunchedEffect(key1 = true) {
		isStartAnim = true
		delay(4000)
		navController.popBackStack()
		navController.navigate(SplashScreens.SplashDetailsScreen.route)
	}
	SplashContent(alphaAnim.value)

}

@Composable
fun SplashContent(alpha : Float) {
	Box(
		contentAlignment = Alignment.Center ,
		modifier = Modifier
			.fillMaxSize()
			.alpha(alpha)
			.background(
				if (isSystemInDarkTheme()) Color
					.Black else Color.Blue
			)
	) {
		Icon(
			modifier = Modifier.size(120.dp) ,
			imageVector = Icons.Filled.Email , contentDescription = "Splash icon" ,
			tint = if (isSystemInDarkTheme()) Color.White else Color.Yellow
		)
	}
}

@Preview()
@Composable
private fun SplashContentPreview() {
	SplashContent(1f)
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SplashContentPreviewDark() {
	SplashContent(1f)

}