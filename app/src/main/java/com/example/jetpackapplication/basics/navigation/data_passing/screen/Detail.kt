package com.example.jetpackapplication.basics.navigation.data_passing.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackapplication.basics.navigation.data_passing.view_model.MyModel
import com.example.jetpackapplication.basics.navigation.data_passing.view_model.SharedViewModel

@Composable
fun Detail(result : MyModel? , sharedViewModel : SharedViewModel) {
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
//			result?.firstName?.let { Text(text = it) }
//			result?.secondName?.let { Text(text = it) }
			sharedViewModel.myModel?.firstName?.let { Text(text = it) }
			sharedViewModel.myModel?.secondName?.let { Text(text = it) }
		}
	}

}
