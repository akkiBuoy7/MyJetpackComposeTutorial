package com.example.jetpackapplication.basics.navigation.serializable_routes.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpackapplication.basics.navigation.serializable_routes.model.DemoModel

@Composable
fun Detail(id : Int , onClick : (demoModel : DemoModel) -> Unit) {

	var firstText by remember {
		mutableStateOf("")
	}

	Box(
		modifier = Modifier.fillMaxSize() ,
		contentAlignment =
		Alignment
			.Center
	) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally ,
			verticalArrangement = Arrangement.spacedBy(10.dp)
		) {
			TextField(value = firstText , onValueChange = { newText ->
				firstText = newText
			})

			Button(modifier = Modifier.fillMaxWidth() , onClick = {
				saveAndNavigate(onClick , firstText , id)

			}) {
				Text(text = "Navigate")
			}
		}
	}

}

fun saveAndNavigate(
	onClick : (demoModel : DemoModel) -> Unit ,
	firstText : String ,
	id : Int
) {

	val obj = DemoModel(id , firstText)
	onClick(obj)

}
