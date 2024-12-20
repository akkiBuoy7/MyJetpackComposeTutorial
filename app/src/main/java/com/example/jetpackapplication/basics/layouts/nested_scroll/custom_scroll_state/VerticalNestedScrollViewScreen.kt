package com.example.jetpackapplication.basics.layouts.nested_scroll.custom_scroll_state

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class , ExperimentalMaterialApi::class)
@Composable
fun NestedScrollview() {
	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					Text(
						text = "VerticalNestedScrollView" ,
						modifier = Modifier.fillMaxWidth() ,
						textAlign = TextAlign.Center
					)
				} ,
				elevation = 0.dp
			)
		}
	) {
		val scope = rememberCoroutineScope()
		val nestedScrollViewState = rememberNestedScrollViewState()
		VerticalNestedScrollView(
			state = nestedScrollViewState ,
			header = {
				Surface(
					modifier = Modifier
						.fillMaxWidth() ,
					color = MaterialTheme.colors.primary ,
				) {
					Column(
						modifier = Modifier.padding(16.dp) ,
						verticalArrangement = Arrangement.spacedBy(20.dp)
					) {
						Text(text = "Make it Easy set of Header")
						Text(text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
					}
				}
			} ,
			horizontalContent = {
				LazyRow(
					modifier = Modifier
						.fillMaxWidth()
						.height(100.dp) // Ensure it takes space
				) {
					items(5) {
						Card(
							modifier = Modifier
								.size(150.dp)
								.padding(5.dp) ,
							shape = RoundedCornerShape(5.dp) ,
							elevation = 5.dp
						) {
							Text(
								text = "Jetpack Compose row ${it + 1}" ,
								modifier = Modifier.padding(15.dp)
							)
						}
					}
				}

			} ,
			verticalContent = {
				LazyColumn {
					items(50) {
						ListItem {
							Card(
								modifier = Modifier
									.fillMaxWidth()
									.padding(5.dp) ,
								shape = RoundedCornerShape(5.dp) ,
								elevation = 5.dp
							) {
								Text(
									text = "Jetpack Compose column ${it + 1}" ,
									modifier = Modifier.padding(15.dp)
								)
							}
						}
					}
				}
			}
		)
	}
}






