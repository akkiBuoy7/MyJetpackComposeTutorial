package com.example.jetpackapplication.basics.on_boarding.boarding_screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpackapplication.basics.on_boarding.util.OnBoardingPage
import com.example.jetpackapplication.basics.on_boarding.viewmodel.WelcomeViewModel
import com.google.accompanist.pager.*

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun Welcome(
	onClick : () -> Unit ,
	welcomeViewModel : WelcomeViewModel = hiltViewModel()
) {
	val pages = listOf(
		OnBoardingPage.First ,
		OnBoardingPage.Second ,
		OnBoardingPage.Third
	)
	val pagerState = rememberPagerState()

	Column(modifier = Modifier.fillMaxSize()) {
		HorizontalPager(
			modifier = Modifier.weight(10f) ,
			count = 3 ,
			state = pagerState ,
			verticalAlignment = Alignment.Top
		) { position ->
			PagerScreen(onBoardingPage = pages[position])
		}
		HorizontalPagerIndicator(
			modifier = Modifier
				.align(Alignment.CenterHorizontally)
				.weight(1f) ,
			pagerState = pagerState
		)
		FinishButton(
			modifier = Modifier.weight(1f) ,
			pagerState = pagerState
		) {
			welcomeViewModel.saveOnBoardingState(completed = true)
			onClick()
//			navController.popBackStack()
//			navController.navigate(BoardingScreen.Home)
		}
	}
}

@Composable
fun PagerScreen(onBoardingPage : OnBoardingPage) {
	Column(
		modifier = Modifier
			.fillMaxWidth() ,
		horizontalAlignment = Alignment.CenterHorizontally ,
		verticalArrangement = Arrangement.Top
	) {
		Image(
			modifier = Modifier
				.fillMaxWidth(0.5f)
				.fillMaxHeight(0.7f) ,
			painter = painterResource(id = onBoardingPage.image) ,
			contentDescription = "Pager Image"
		)
		Text(
			modifier = Modifier
				.fillMaxWidth() ,
			text = onBoardingPage.title ,
			fontSize = MaterialTheme.typography.headlineMedium.fontSize ,
			fontWeight = FontWeight.Bold ,
			textAlign = TextAlign.Center
		)
		Text(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 40.dp)
				.padding(top = 20.dp) ,
			text = onBoardingPage.description ,
			fontSize = MaterialTheme.typography.bodyMedium.fontSize ,
			fontWeight = FontWeight.Medium ,
			textAlign = TextAlign.Center
		)
	}
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinishButton(
	modifier : Modifier ,
	pagerState : PagerState ,
	onClick : () -> Unit
) {
	Row(
		modifier = modifier
			.padding(horizontal = 40.dp) ,
		verticalAlignment = Alignment.Top ,
		horizontalArrangement = Arrangement.Center
	) {
		AnimatedVisibility(
			modifier = Modifier.fillMaxWidth() ,
			visible = pagerState.currentPage == 2
		) {
			Button(
				onClick = onClick ,
//				colors = ButtonDefaults.buttonColors(
//					contentColor = Color.White
//				)
			) {
				Text(text = "Finish")
			}
		}
	}
}

@Composable
@Preview(showBackground = true)
fun FirstOnBoardingScreenPreview() {
	Column(modifier = Modifier.fillMaxSize()) {
		PagerScreen(onBoardingPage = OnBoardingPage.First)
	}
}

@Composable
@Preview(showBackground = true)
fun SecondOnBoardingScreenPreview() {
	Column(modifier = Modifier.fillMaxSize()) {
		PagerScreen(onBoardingPage = OnBoardingPage.Second)
	}
}

@Composable
@Preview(showBackground = true)
fun ThirdOnBoardingScreenPreview() {
	Column(modifier = Modifier.fillMaxSize()) {
		PagerScreen(onBoardingPage = OnBoardingPage.Third)
	}
}