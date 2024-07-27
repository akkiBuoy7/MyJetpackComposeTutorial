package com.example.jetpackapplication.basics.widgets.lazy_column

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyStickyHeaderLazyColumn() {

	val sections = listOf("A" , "B" , "C" , "D" , "E" , "F" , "G")

	LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {

		sections.forEach { sec ->
			stickyHeader {
				Text(
					modifier = Modifier
						.fillMaxWidth()
						.background(Color.Blue)
						.padding(12.dp) ,
					text = "Section $sec" ,
					color = Color.White ,
					fontWeight = FontWeight.Bold
				)

			}
			items(items = PersonRepository().getAllData()) {
				CustomItem(model = it) {
					Log.d("MyLazyColumn" , "OnClick: ${it.firstName}")
				}
			}
		}

	}

}