package com.example.jetpackapplication.basics.view_pager

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackapplication.R
import kotlinx.coroutines.launch

@Composable
fun MyViewPager() {
	val animals = listOf(
		R.drawable.cat ,
		R.drawable.dog ,
		R.drawable.chicken ,
	)

	val pagerState = rememberPagerState(initialPage =
	                                    0 , pageCount
	                                    = { animals.size })
	val scope = rememberCoroutineScope()
	Box(modifier = Modifier.fillMaxSize()) {

		HorizontalPager(
			state = pagerState ,
			key = { animals[it] } ,
			pageSize = PageSize.Fill
		) { index ->
			Image(
				painter = painterResource(id = animals[index]) ,
				contentDescription = null ,
				contentScale = ContentScale.Crop ,
				modifier = Modifier.fillMaxSize()
			)
		}

		Column(modifier = Modifier
			.fillMaxWidth()
			.offset(y = (16).dp)
			.align(Alignment.TopCenter),
		       horizontalAlignment = Alignment.CenterHorizontally) {

			Row {
				repeat(animals.size){
					CustomIndicator(selected = pagerState.currentPage==it)
				}
			}
		}
		Box(
			modifier = Modifier
				.offset(y = -(16).dp)
				.fillMaxWidth(0.5f)
				.clip(RoundedCornerShape(100))
				.background(MaterialTheme.colorScheme.background)
				.padding(8.dp)
				.align(Alignment.BottomCenter)
		) {
			IconButton(
				onClick = {
					scope.launch {
						pagerState.animateScrollToPage(
							pagerState.currentPage - 1
						)
					}
				} ,
				modifier = Modifier.align(Alignment.CenterStart)
			) {
				Icon(
					imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft ,
					contentDescription = "Go back"
				)
			}
			IconButton(
				onClick = {
					scope.launch {
						pagerState.animateScrollToPage(
							pagerState.currentPage + 1
						)
					}
				} ,
				modifier = Modifier.align(Alignment.CenterEnd)
			) {
				Icon(
					imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight ,
					contentDescription = "Go forward"
				)
			}
		}
	}
}

@Composable
fun CustomIndicator(selected : Boolean) {
	Box(
		modifier = Modifier
			.padding(2.dp)
			.background(
				color = if (selected) Color.Red
				else Color.Gray , shape = CircleShape
			).border(
				2.dp,
				color = if (selected) Color.Black
				else Color.Gray , shape = CircleShape
			)
			.size(10.dp)
	)
}

@Preview
@Composable
private fun MyViewPagerPreview() {
	MyViewPager()
}