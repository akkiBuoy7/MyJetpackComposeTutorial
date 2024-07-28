package com.example.jetpackapplication.basics.navigation.serializable_routes.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpackapplication.basics.navigation.serializable_routes.model.DemoModel

@Composable
fun Profile(demoModel : DemoModel) {
	Box(
		modifier = Modifier.fillMaxSize() ,
		contentAlignment =
		Alignment
			.Center
	) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement =
			Arrangement.spacedBy(30.dp)
		) {
			Text(text = "${demoModel.name} : ${demoModel.id}")
		}
	}

}