package com.example.jetpackapplication.basics.item_selection.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpackapplication.basics.item_selection.data.DemoItemSelection
import com.example.jetpackapplication.basics.item_selection.nav.ItemSelectionScreens
import com.example.jetpackapplication.basics.item_selection.viewModel.ItemSelectionListingViewModel

@Composable
fun ItemSelectionDashScreenContent(
	navController : NavHostController ,
	viewModel : ItemSelectionListingViewModel ,
) {

	val demoItems : List<DemoItemSelection> by viewModel.demoItems.collectAsState(initial = emptyList())
	Column(modifier = Modifier.fillMaxSize()) {
		if (demoItems.isEmpty()) {
			Box(
				modifier = Modifier.fillMaxSize() ,
				contentAlignment = Alignment.Center
			) {
				CircularProgressIndicator(
					color = Color.White ,
					strokeWidth = 2.dp ,
					modifier = Modifier.size(30.dp)
				)
			}
		} else {
			LazyColumn(
				modifier = Modifier
					.padding(5.dp)
					.weight(1f)
			) {
				items(demoItems.size) { index ->
					MyListItemCompose(
						demoItems[index] ,
						index ,
						viewModel ,
					)
				}
			}
		}
		Button(onClick = {
			val selectedItems = viewModel.getSelectedItems()
			navController.navigate(ItemSelectionScreens.DetailsScreen(selectedItems))
		} , modifier = Modifier.align(Alignment.CenterHorizontally)) {
			Text(text = "Next")
		}
	}

}

@Composable
fun MyListItemCompose(
	item : DemoItemSelection ,
	index : Int ,
	viewModel : ItemSelectionListingViewModel ,
) {
	Card(onClick = {
		viewModel.toggleSelection(index)
	} , modifier = Modifier
		.padding(5.dp)
		.fillMaxSize()) {
		Row(
			modifier = Modifier
				.height(100.dp)
				.fillMaxWidth() ,
			horizontalArrangement = Arrangement.SpaceAround
		) {
			Image(
				painter = painterResource(
					id = item.img
				) ,
				contentDescription = "image" ,
				modifier = Modifier
					.size(50.dp)
					.weight(0.2f)
					.padding(top = 7.dp , start = 7.dp)
					.clip(
						shape = CircleShape
					)
					.background(color = Color.Gray) ,
			)

			ItemDesc(
				item ,
				Modifier
					.padding(start = 15.dp , top = 7.dp)
					.weight(0.8f)
			)
			Box(
				contentAlignment = Alignment.TopEnd , modifier = Modifier.padding(
					end =
					5.dp
				)
			) {
				if (item.isSelected)
					Icon(
						imageVector = Icons.Default.Check ,
						contentDescription = "Selected" ,
						tint = Color.Red ,
						modifier = Modifier
							.size(30.dp)
							.padding(top = 5.dp , end = 10.dp)
					)
			}
		}
	}
}

@Composable
fun ItemDesc(item : DemoItemSelection , modifier : Modifier) {
	Column(modifier = modifier) {
		Text(
			text = item.role ,
			color = Color.Red ,
			style = TextStyle(fontWeight = FontWeight.Bold)
		)
		Spacer(
			modifier = Modifier.height(
				10.dp
			)
		)
		Text(
			text = myAnnotatedString(item.desc) ,
		)
	}
}

fun myAnnotatedString(desc : String) : AnnotatedString {
	return buildAnnotatedString {
		withStyle(
			ParagraphStyle(
				textAlign = TextAlign.Center ,
			)
		) {
			withStyle(
				SpanStyle(
					color = Color.White ,
					fontWeight = FontWeight.Bold
				)
			) {
				append(desc[0])
			}

			desc.drop(1).forEach { char ->
				append(char)
			}

		}
	}
}


