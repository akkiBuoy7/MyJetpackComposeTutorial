package com.example.jetpackapplication.basics.widgets

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyAnimatedShimmer() {

	val shimmerColors = listOf(
		Color.LightGray.copy(alpha = 0.6f) ,
		Color.LightGray.copy(alpha = 0.2f) ,
		Color.LightGray.copy(alpha = 0.6f)
	)

	val transition = rememberInfiniteTransition()
	val transitionAnim = transition.animateFloat(
		initialValue = 0f ,
		targetValue = 1000f ,
		animationSpec = infiniteRepeatable(
			animation = tween(
				durationMillis = 1000 ,
				easing = FastOutSlowInEasing
			) ,
			repeatMode = RepeatMode.Reverse
		) , label = "shimmer animation"
	)

	val brush = Brush.linearGradient(
		colors = shimmerColors ,
		start = Offset.Zero ,
		end = Offset(x = transitionAnim.value , y = transitionAnim.value)
	)

	ShimmerGridItem(brush = brush)

}

@Composable
fun ListShimmer() {

	LazyColumn(
		contentPadding = PaddingValues(all = 20.dp) ,
		verticalArrangement = Arrangement.spacedBy(20.dp)
	) {
		items(15) { index ->
			MyAnimatedShimmer()
		}

	}
	
}

@Composable
fun ShimmerGridItem(brush : Brush) {

	Row(modifier = Modifier.padding(all = 10.dp),
		verticalAlignment = Alignment.CenterVertically) {

		Spacer(
			modifier = Modifier
				.size(80.dp)
				.clip(CircleShape)
				.background(brush)
		)
		Spacer(modifier = Modifier.width(10.dp))

		Column(verticalArrangement = Arrangement.Center) {
			Spacer(modifier = Modifier
				.background(brush)
				.fillMaxWidth()
				.clip(RoundedCornerShape(10.dp))
				.height(20.dp))
			Spacer(modifier = Modifier.height(5.dp))
			Spacer(modifier = Modifier
				.background(brush)
				.fillMaxWidth()
				.clip(RoundedCornerShape(10.dp))
				.height(20.dp))
		}

	}

}


@Composable
@Preview
private fun MyShimmerPreview() {

	ShimmerGridItem(Brush.linearGradient(
		listOf(
			Color.LightGray.copy(alpha = 0.6f) ,
			Color.LightGray.copy(alpha = 0.2f) ,
			Color.LightGray.copy(alpha = 0.6f)
		)))

}