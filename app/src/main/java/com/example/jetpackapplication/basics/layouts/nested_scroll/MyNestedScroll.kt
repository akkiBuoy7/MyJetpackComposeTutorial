package com.example.jetpackapplication.basics.layouts.nested_scroll

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpackapplication.R

@Composable
fun MyNestedScroll() {

	LazyColumn(
		modifier = Modifier.fillMaxSize() ,
		contentPadding = PaddingValues(16.dp)
	) {

		item {
			Image(
				painter = painterResource(id = R.drawable.nature) ,
				contentScale = ContentScale.Crop ,
				contentDescription = "header" , modifier = Modifier
					.height(300.dp)
					.fillMaxWidth()
					.padding(bottom = 10.dp)
			)
		}

		item {
			SubList1()
		}

		// This won't work as size of subList2 LazyColumn is not known

//		item {
//			SubList2()
//		}

		item {
			Spacer(modifier = Modifier.height(20.dp))
		}
		subList2()
	}
}

//@Composable
//fun SubList2() {
//LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
//	items(10) {
//		Box(modifier = Modifier
//			.size(100.dp)
//			.background(Color.Gray))
//	}
//}
//}

fun LazyListScope.subList2() {

	items(10) { index ->
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.height(100.dp)
				.background(Color.Gray)
		)
		// Add spacer after each item except the last one to create a gap
		if (index < 9) {
			Spacer(modifier = Modifier.height(16.dp)) // Adjust the gap as needed
		}
	}

}

@Composable
fun SubList1() {

	LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
		items(10) {
			Box(
				modifier = Modifier
					.size(100.dp)
					.background(Color.Red)
			)
		}
	}
}
