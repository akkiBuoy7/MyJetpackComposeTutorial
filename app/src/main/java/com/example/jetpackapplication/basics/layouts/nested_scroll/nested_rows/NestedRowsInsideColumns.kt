package com.example.jetpackapplication.basics.layouts.nested_scroll.nested_rows

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackapplication.R
import kotlinx.coroutines.delay

@Composable
fun NestedRowsInsideColumn() {

	// Use 'remember' to store the data state and handle the loading/error states
	var parentItemsList by remember { mutableStateOf<List<ParentDataClass>?>(null) }
	val isLoading = remember { mutableStateOf(true) }
	val errorMessage = remember { mutableStateOf<String?>(null) }

	// Launch a coroutine when the composable is first composed
	LaunchedEffect(Unit) {
		try {
			// Simulate fetching data in a coroutine scope
			parentItemsList = setData()
			Log.d("TAG" , "NestedRowsInsideColumn: inside")

			// Update the state once data is fetched
			isLoading.value = false
		} catch (e : Exception) {
			// Handle the error
			errorMessage.value = "Error loading data: ${e.message}"
			isLoading.value = false
		}
	}

	Log.d("TAG" , "NestedRowsInsideColumn: outside")

	// A surface container using the 'background' color from the theme
	Surface(
		modifier = Modifier.fillMaxSize() , color = Color(0xF11F1D1E)
	) {

		if (isLoading.value) {
			Box(
				modifier = Modifier
					.background(Color.Black)
					.fillMaxSize() ,
				contentAlignment = Alignment.Center
			) {

				CircularProgressIndicator(
					modifier = Modifier
						.fillMaxWidth()
						.aspectRatio(1f)
				)
			}
		} else if (errorMessage.value != null) {
			// Show an error message if data fetching failed
			Text(
				text = errorMessage.value!! ,
			)
		} else {
			LazyColumn(
				contentPadding = PaddingValues(15.dp) , modifier = Modifier.fillMaxSize()
			) {
				parentItemsList?.let {
					items(it.size) { index ->
						ColumnItemUI(
							parentItemsList = parentItemsList!! , itemIndex = index
						)
					}
				}
			}
		}

	}

}

private suspend fun setData() : List<ParentDataClass> {

	// Simulate a delay (e.g., network or database call)
	delay(2000L)
	val images = listOf(
		ChildDataClass(R.drawable.cat) ,
		ChildDataClass(R.drawable.book10) ,
		ChildDataClass(R.drawable.book11) ,
		ChildDataClass(R.drawable.cat) ,
		ChildDataClass(R.drawable.book10) ,
		ChildDataClass(R.drawable.book11) ,
		ChildDataClass(R.drawable.cat) ,
		ChildDataClass(R.drawable.book10) ,
		ChildDataClass(R.drawable.book11) ,
	)

	return listOf(
		ParentDataClass("Novel:" , images) ,
		ParentDataClass("Best Seller:" , images.shuffled()) ,
		ParentDataClass("History:" , images.shuffled()) ,
		ParentDataClass("Favorite:" , images.reversed()) ,
		ParentDataClass("Drama:" , images.shuffled()) ,
		ParentDataClass("Crime:" , images)
	)
}

@Composable
fun ColumnItemUI(
	parentItemsList : List<ParentDataClass> , itemIndex : Int
) {
	Card(
		Modifier.padding(12.dp) , colors = CardDefaults.cardColors(
			containerColor = Color.DarkGray
		)
	) {
		Text(
			text = parentItemsList[itemIndex].title ,
			Modifier.padding(12.dp) ,
			fontSize = 28.sp ,
			fontWeight = FontWeight.Bold ,
			color = Color(0xFFFFC107)
		)
		LazyRow(contentPadding = PaddingValues(12.dp)) {
			items(parentItemsList[itemIndex].childList.size) { it ->
				RowItemUI(
					childList = parentItemsList[itemIndex].childList , rowItemIndex = it
				)
			}
		}
	}
}

@Composable
fun RowItemUI(
	childList : List<ChildDataClass> , rowItemIndex : Int
) {
	Box(
		Modifier
			.height(200.dp)
			.width(160.dp)
			.padding(horizontal = 10.dp)
			.clip(RoundedCornerShape(12.dp))
			.background(Color(0xF1201E1F))
	) {
		Image(
			painter = painterResource(id = childList[rowItemIndex].image) ,
			contentDescription = "" ,
			Modifier.fillMaxSize()
		)
	}
}