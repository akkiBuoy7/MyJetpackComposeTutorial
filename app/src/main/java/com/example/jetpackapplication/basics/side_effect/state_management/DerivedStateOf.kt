package com.example.jetpackapplication.basics.side_effect.state_management

import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/*
 Provides a state
 Can derive a state by computing logic on multiple states
 */

fun showToast(message : String , context : ComponentActivity) {
	Toast.makeText(context , message , Toast.LENGTH_SHORT).show()
}

@Composable
fun MyDerivedStateOf() {
	val context = LocalContext.current as ComponentActivity

	var counter by remember {
		Log.i("MyDerivedStateOf" , "Counter initialized")
		mutableStateOf(0)
	}

	// derived state delegated by remember
	val counterBackGroundColor by remember(counter) {
		// derives a new state based on a condition
		derivedStateOf {
			if (counter % 2 == 0) Color.Green else Color.Red
		}
	}

	val increaseCounter = {
		counter++
		Log.i("MyDerivedStateOf" , "Increase counter: ${counter}")
	}

	val decreaseCounter = {
		if (counter == 0) {
			showToast("Counter cannot be less than 0" , context)
		} else {
			counter--
			Log.i("MyDerivedStateOf" , "Decrease counter: ${counter}")
		}
	}

	Log.i("Counter" , " Counter value $counter")

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
				Button(
					onClick = { decreaseCounter.invoke() } ,
					modifier = Modifier
						.align(Alignment.CenterVertically)
						.padding(10.dp)
						.weight(0.4f)
				) {
					Text(text = "Decrease")
				}

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

				Button(
					onClick = { increaseCounter.invoke() } ,
					modifier = Modifier
						.align(Alignment.CenterVertically)
						.padding(10.dp)
						.weight(.4f)
				) {
					Text(text = "Increase")
				}
			}
		}

	}

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	MyDerivedStateOf()
}