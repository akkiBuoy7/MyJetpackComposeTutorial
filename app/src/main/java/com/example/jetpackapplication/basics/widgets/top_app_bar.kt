package com.example.jetpackapplication.basics.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {

	val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
	Scaffold(
		modifier = Modifier
			.fillMaxSize()
			// for changing opacity as list is scrolled
			.nestedScroll(scrollBehavior.nestedScrollConnection) ,
		topBar = {
			androidx.compose.material3.TopAppBar(
				title = {
					Text(text = "My notes")
				} ,
				navigationIcon = {
					IconButton(onClick = { /*TODO*/ }) {
						Icon(
							imageVector = Icons.Default.Menu ,
							contentDescription = "Go back"
						)
					}
				} ,
				actions = {
					IconButton(onClick = { /*TODO*/ }) {
						Icon(
							imageVector = Icons.Default.FavoriteBorder ,
							contentDescription = "Mark as favorite"
						)
					}
					IconButton(onClick = { /*TODO*/ }) {
						Icon(
							imageVector = Icons.Default.Edit ,
							contentDescription = "Edit notes"
						)
					}
				} ,
				scrollBehavior = scrollBehavior ,
			)
		}
	) { values ->
		ColumnContent(values)

	}
}

@Composable
private fun ColumnContent(values : PaddingValues) {
	LazyColumn(
		modifier = Modifier
			.fillMaxSize()
			// need to pass scaffold padding to the root layout inside scaffold
			.padding(values)
	) {
		items(100) {
			Text(
				text = "Item$it" ,
				modifier = Modifier.padding(16.dp)
			)
		}
	}
}

