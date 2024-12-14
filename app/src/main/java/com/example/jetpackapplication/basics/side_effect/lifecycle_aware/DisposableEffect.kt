package com.example.jetpackapplication.basics.side_effect.lifecycle_aware

import android.util.Log
import androidx.activity.ComponentActivity
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackapplication.basics.side_effect.state_management.showToast
import kotlinx.coroutines.delay

/*
  It is life cycle aware
  Can dispose callbacks or unregister listeners
 */

suspend fun startCounterUpdated(
	increment : () -> Unit ,
	onCounterEnded : () -> Unit
) {
	for (i in 1..10) {
		delay(1000) // Use delay from kotlinx.coroutines
		Log.i("MyDisposableState" , " Counter running")
		increment()
	}
	onCounterEnded()
}

@Composable
fun MyDisposableState() {
	// parent composable
	val context = LocalContext.current as ComponentActivity
	var counter by remember {
		Log.i("MyDisposableState" , "Counter initialized")
		mutableStateOf(0)
	}

	var counterMessage by remember {
		mutableStateOf("Counter not yet started")
	}

	val increaseCounter = {
		counter++
		counterMessage = "Counter Increased to $counter"

	}

	// derived state delegated by remember
	val counterBackGroundColor by remember(counter) {
		// derives a new state based on a condition
		derivedStateOf {
			if (counter % 2 == 0) Color.Green else Color.Red
		}
	}

	DisposableEffect(key1 = Unit) {
		onDispose {
			counter = 0
			Log.i("MyDisposableState" , "Counter disposed $counter ")
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

			// Child composable
			CounterMessage(
				counterMessage ,
				context = context ,
				increaseCounter = { increaseCounter() }
			)


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

@Composable
fun CounterMessage(
	counterMessage : String ,
	context : ComponentActivity ,
	increaseCounter : () -> Unit
) {
	/*
	counterMessage -> If only counterMessage was used then the state would print at the
	 end "counter not yer started" as the initial state only was shared to child
	 composable for sharing the updated composable we should use rememberUpdatedState
	 which will always track the current value of the state. It ensures proper sync
	 between child and parent composable
	 */
	val counterMessageLatest by rememberUpdatedState(counterMessage)

	Log.i(
		"MyRememberUpdatedState" ,
		" Rendering CounterMessage with $counterMessageLatest"
	)
	LaunchedEffect(key1 = Unit) {
		Log.i("MyRememberUpdatedState" , " LaunchedEffect with $counterMessageLatest")
		startCounterUpdated({ increaseCounter() }) {
			Log.i("MyRememberUpdatedState" , "Counter Ended with $counterMessageLatest")
			showToast("Counter Ended" , context)
		}
	}
	Text(
		text = counterMessageLatest ,
		fontSize = 20.sp ,
		modifier = Modifier.padding(20.dp)
	)
}

@Preview(showBackground = true)
@Composable
fun DefaultMyDisposableStatePreview() {
	MyDisposableState()
}