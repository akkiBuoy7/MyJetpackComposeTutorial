package com.example.jetpackapplication.basics.side_effect.state_management

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/*

 */

suspend fun startCounterProduce(
	increment : () -> Unit ,
	onCounterEnded : () -> Unit
) {
	for (i in 1..10) {
		delay(1000) // Use delay from kotlinx.coroutines
		increment()
	}
	onCounterEnded()
}

enum class CounterState {
	NOTSTARTED , INPROGRESS , COMPLETED
}

suspend fun fetchIconFromNetwork(counterState : CounterState) : ImageVector {
	delay(1000)
	val icon = when (counterState) {
		CounterState.NOTSTARTED -> Icons.Default.PlayCircle
		CounterState.COMPLETED -> Icons.Default.StopCircle
		else -> Icons.Default.PauseCircle
	}
	return icon
}

@Composable
fun MyProduceState() {
	// parent composable
	val context = LocalContext.current as ComponentActivity
	var counter by remember {
		mutableStateOf(0)
	}

	var counterMessage by remember {
		mutableStateOf("Counter not yet started")
	}

	val increaseCounter = {
		counter++
		counterMessage = "Counter Increased to $counter"

	}

	var counterState by remember {
		mutableStateOf(CounterState.NOTSTARTED)
	}

	SideEffect {
		counterState = when (counter) {
			0 -> CounterState.NOTSTARTED
			10 -> CounterState.COMPLETED
			else -> CounterState.INPROGRESS
		}
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
		}
	}

	LaunchedEffect(key1 = Unit) {
		startCounterProduce({ increaseCounter() }) {
			showToast("Counter Ended" , context)
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
			DisplayIcon(
				counterState = counterState ,
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
fun DisplayIcon(
	counterState : CounterState ,
) {

	// IncreaseCounter -> Fetch icon based on Counter -> Display icon
	/*
	If we achieve this by LaunchedEffect the code is not clean
	 */

	/*
	var icon by remember {
		mutableStateOf<ImageVector?>(null)
	}
	val counterStateLatest by rememberUpdatedState(counterState)

	Log.i("MyProduceState" , "counterStateLatest: $counterStateLatest")
	LaunchedEffect(key1 = counterStateLatest) {
		icon = fetchIconFromNetwork(counterStateLatest)
	}
   */

	/*
	 Using produceState we can directly produce the iconState from network call
	 It provides a ProduceStateScope which extends coroutineScope thus
	 we can execute long running function and set the result to value
	 */
	val iconState =
		produceState<ImageVector?>(initialValue = null , key1 = counterState) {
			value = fetchIconFromNetwork(counterState)
		}

	Box(
		modifier = Modifier
			.width(40.dp)
			.height(40.dp)
	) {
		iconState.value?.let {
			Icon(
				imageVector = it , contentDescription = "network icon" , modifier =
				Modifier.size(50.dp)
			)
		}

	}

}

@Preview(showBackground = true)
@Composable
fun DefaultMyProduceStatePreview() {
	MyProduceState()
}