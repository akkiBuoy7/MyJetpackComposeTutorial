package com.example.jetpackapplication.basics.side_effect.lifecycle_aware

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/*
  Provides a coroutine scope for running long running operations
  Launch at initial composition only unless key value is not static
  LaunchedEffect is tied to the composable lifecycle
 */

suspend fun startCounterLaunched(
	increment : () -> Unit ,
) {
	for (i in 1..100) {
		delay(1000) // Use delay from kotlinx.coroutines
		Log.i("MyRememberCoroutineScope" , " Counter running")
		increment()
	}
}

@Composable
fun MyLaunchedEffect() {
	var counter by remember {
		Log.i("MyLaunchedEffect" , "Counter initialized")
		mutableStateOf(0)
	}

	val increaseCounter = {
		counter++
	}

	/*
	 It will launch once
	 */
//	LaunchedEffect(key1 = Unit) {
//		Log.i("MyLaunchedEffect" , "Once launched: ${counter}")
//		startCounterLaunched { increaseCounter() }
//	}

	/*
	 It will launch every time counter value is updated
	 */
	LaunchedEffect(key1 = counter) {
		Log.i("MyLaunchedEffect" , "Launched multiple times: ${counter}")
		startCounterLaunched { increaseCounter() }
	}

	// derived state delegated by remember
	val counterBackGroundColor by remember(counter) {
		// derives a new state based on a condition
		derivedStateOf {
			if (counter % 2 == 0) Color.Green else Color.Red
		}
	}

	Surface(
		//Aligns the content of this component along the specified [alignment] lines.
		color = MaterialTheme.colorScheme.background ,
		modifier = Modifier.fillMaxSize() ,
	) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally ,
			verticalArrangement = Arrangement.Center ,
			modifier = Modifier
				.fillMaxSize(1f)
				.padding(1.dp)
		) {

			Row(
				verticalAlignment = Alignment.CenterVertically ,

				) {

				Text(
					text = "$counter" , modifier = Modifier
						.padding(20.dp)
						.background(counterBackGroundColor)
						.width(50.dp)
						.height(50.dp)
						.weight(.2f) ,
					textAlign = androidx.compose.ui.text.style.TextAlign.Center ,
					fontSize = 35.sp
				)
			}
		}

	}

}

@Preview(showBackground = true)
@Composable
fun DefaultMyLaunchedEffectPreview() {
	MyLaunchedEffect()
}