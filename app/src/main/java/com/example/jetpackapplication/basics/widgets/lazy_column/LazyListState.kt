package com.example.jetpackapplication.basics.widgets.lazy_column

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun LazyListStateContent() {
	val lazyListSate = rememberLazyListState()

	Scaffold { padding ->
		Box(
			modifier = Modifier
				.fillMaxSize()
				.padding(padding)
		) {
			MyList(lazyListSate)
			TopBar(lazyListSate)
			Text(
				text = if (lazyListSate.isScrolled) "Scrolling..." else "Idle" ,
				fontSize =
				MaterialTheme
					.typography.headlineMedium.fontSize ,
				fontWeight = FontWeight.Bold ,
				color = if (lazyListSate.isScrolled) Color.Blue else Color.Black ,
				modifier = Modifier.align(Alignment.Center)
			)
		}

	}
}

val LazyListState.isScrolled : Boolean
	get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0

@Composable
fun MyList(lazyListState : LazyListState) {

	val items1 = remember {
		List(size = 25) {
			it
		}
	}

	LazyColumn(
		contentPadding = PaddingValues(25.dp) ,
		state = lazyListState ,
		verticalArrangement = Arrangement.spacedBy(10.dp),
		modifier = Modifier.padding(top = 56.dp)
	) {
		items(items = items1 , key = { it }) {
			ItemHolder()
		}
	}

}

val TOP_BAR_HEIGHT = 56.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(lazyListState : LazyListState) {

	TopAppBar(
		colors = TopAppBarDefaults.topAppBarColors(
			containerColor = MaterialTheme.colorScheme.primaryContainer ,
			titleContentColor = MaterialTheme.colorScheme.primary ,
		) ,
		title = {
			Text(
				textAlign = TextAlign.Center ,
				color = Color.Black ,
				text = "Title" ,
				style = TextStyle(
					fontSize = MaterialTheme.typography.headlineMedium.fontSize ,
					color = MaterialTheme.colorScheme.surface
				)
			)/*TODO*/
		} ,
		modifier = Modifier
			.fillMaxWidth(

			)
			.background(color = Color.Blue)
			.animateContentSize
				(animationSpec = tween(durationMillis = 300))
			.height(
				height = if (lazyListState.isScrolled) 0.dp else TOP_BAR_HEIGHT

			)

	)
}

@Composable
fun ItemHolder() {
	Spacer(
		modifier = Modifier
			.fillMaxWidth()
			.height(40.dp)
			.background(Color.Gray)
			.clip(
				RoundedCornerShape(5.dp)
			)
	)

}