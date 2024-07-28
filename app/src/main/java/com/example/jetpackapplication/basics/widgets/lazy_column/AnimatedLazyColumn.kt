package com.example.jetpackapplication.basics.widgets.lazy_column

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter" ,
              "UnusedMaterial3ScaffoldPaddingParameter"
)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyAnimatedLazyColumn() {
	val repo = PersonRepository()
	var data by remember {
		mutableStateOf(repo.getAllData())
	}

	Scaffold {
		Column(
			verticalArrangement = Arrangement.spacedBy(10.dp) ,
			modifier = Modifier
				.fillMaxSize() ,
		) {
			LazyColumn(
				verticalArrangement = Arrangement.spacedBy(10.dp) , modifier = Modifier
					.weight(1f) // must use weight when putting a widget below LazyColumn
			) {
				items(items = data , key = { it.id }) { obj ->
					Text(
						text = obj.firstName ,
						fontWeight = FontWeight.Bold ,
						modifier = Modifier
							.fillMaxWidth()
							.background(color = Color.LightGray)
							.padding(24.dp)
							.animateItemPlacement( // for animating item shuffle
								animationSpec = tween
									(durationMillis = 600)
							)
					)
				}

			}

			Button(
				onClick = { data = data.shuffled() } ,
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 24.dp)
			) {
				Text(text = "Shuffle")
			}
		}

	}

}