package com.example.jetpackapplication.basics.navigation.data_passing.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapplication.basics.navigation.data_passing.view_model.MyModel

@Composable
fun Home(navController : NavHostController) {
	var firstText by remember {
		mutableStateOf("")
	}

	var lastText by remember {
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

			TextField(value = lastText , onValueChange = { newText ->
				lastText = newText
			})

			Button(modifier = Modifier.fillMaxWidth() , onClick = {
				saveParcelableDataNavigate(firstText,lastText,navController)
			}) {
				Text(text = "Navigate")
			}
		}
	}

}

fun saveParcelableDataNavigate(
	firstText : String ,
	lastText : String ,
	navController : NavHostController
) {
	val obj = MyModel(firstName = firstText , secondName = lastText)

	navController.currentBackStackEntry?.savedStateHandle?.set(
		key = "myModel" ,
		value = obj
	)

	navController.navigate(Screen.Detail.route)
}

@Preview
@Composable
private fun HomePreview() {
	Home(rememberNavController())
}