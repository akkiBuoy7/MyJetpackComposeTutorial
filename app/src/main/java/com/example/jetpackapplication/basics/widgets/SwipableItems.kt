package com.example.jetpackapplication.basics.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import kotlinx.coroutines.delay

@Composable
fun SwipableListContent() {

	val programmingLanguages = remember {
		mutableStateListOf(
			"Kotlin",
			"Java",
			"C++",
			"C#",
			"JavaScript",
		)
	}

	LazyColumn(
		modifier = Modifier
			.fillMaxSize()
	) {
		items(
			items = programmingLanguages,
			key = { it }
		) { language ->
			SwipeToDeleteContainer(
				item = language,
				onDelete = {
					programmingLanguages -= language
				}
			) { language ->
				Text(
					text = language,
					modifier = Modifier
						.fillMaxWidth()
						.background(MaterialTheme.colorScheme.background)
						.padding(16.dp)
				)
			}
		}
	}
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T> SwipeToDeleteContainer(
	item: T,
	onDelete: (T) -> Unit,
	animationDuration: Int = 500,
	content: @Composable (T) -> Unit
) {
	var isRemoved by remember {
		mutableStateOf(false)
	}
	val state = rememberDismissState(
		confirmStateChange = { value ->
			if (value == DismissValue.DismissedToStart) {
				isRemoved = true
				true
			} else {
				false
			}
		}
	)

	LaunchedEffect(key1 = isRemoved) {
		if(isRemoved) {
			delay(animationDuration.toLong())
			onDelete(item)
		}
	}

	AnimatedVisibility(
		visible = !isRemoved,
		exit = shrinkVertically(
			animationSpec = tween(durationMillis = animationDuration),
			shrinkTowards = Alignment.Top
		) + fadeOut()
	) {
		SwipeToDismiss(
			state = state,
			background = {
				DeleteBackground(swipeDismissState = state)
			},
			dismissContent = { content(item) },
			directions = setOf(DismissDirection.EndToStart)
		)
	}
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DeleteBackground(
	swipeDismissState: DismissState
) {
	val color = if (swipeDismissState.dismissDirection == DismissDirection.EndToStart) {
		Color.Red
	} else Color.Transparent

	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(color)
			.padding(16.dp),
		contentAlignment = Alignment.CenterEnd
	) {
		Icon(
			imageVector = Icons.Default.Delete,
			contentDescription = null,
			tint = Color.White
		)
	}
}