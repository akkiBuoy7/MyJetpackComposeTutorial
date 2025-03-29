package com.example.jetpackapplication.basics.dropdown

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.TextField
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyDropdown() {

	var isExpanded by remember {
		mutableStateOf(false)
	}

	val dList = listOf("Select option" , "one" , "two" , "three" , "four" , "five")

	var selectedValue by remember {
		mutableStateOf(dList[0])
	}



	Column(
		modifier = Modifier
			.fillMaxWidth()
			.fillMaxSize()
			.padding(horizontal = 8.dp) ,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		ExposedDropdownMenuBox(
			expanded = isExpanded , onExpandedChange = {
				isExpanded = !isExpanded
			} , modifier = Modifier.padding(
				10.dp
			)
		) {
			TextField(
				value = selectedValue , onValueChange = {} , readOnly = true ,
				trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
			)
			ExposedDropdownMenu(expanded = isExpanded , onDismissRequest = {
				isExpanded = false
			}) {
				dList.forEachIndexed { index , item ->
					Log.d("TAG" , "MyDropdown: ${dList[0]}")
					Log.d("TAG" , "selectedValue: $selectedValue")
					Log.d("TAG" , "isVisible: ${selectedValue != dList[0]}")
					DropdownMenuItem(
						text = { Text(text = item) } ,
						onClick = {
							selectedValue = dList[index]
							isExpanded = false
						} ,
					)
				}
			}

		}
		AnimatedVisibility(visible = selectedValue != dList[0]) {
			Text(
				text = "Currently selected item is $selectedValue" ,
				Modifier.padding(10.dp)
			)
		}
	}
}

@Preview
@Composable
private fun MyDropdownPrev() {
	MyDropdown()
}