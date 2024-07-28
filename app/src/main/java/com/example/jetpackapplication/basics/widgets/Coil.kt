package com.example.jetpackapplication.basics.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.jetpackapplication.R

@Composable
fun MyCoilImage() {
	Box(
		modifier = Modifier.fillMaxSize() ,
		contentAlignment = Alignment.Center
	) {

		val painter = rememberAsyncImagePainter(
			model = ImageRequest.Builder(LocalContext.current)
				.data("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQq9KTp6k5NRv5a6pf6VI_SLNum6J4612jaDQ&s")
				.placeholder(R.drawable.ic_image_placeholder)
				.error(R.drawable.ic_error)
				.crossfade(1000)
				.transformations(
					CircleCropTransformation()
				)
				.build()
		)
		val painterState = painter.state
		Image(painter = painter , contentDescription = "Image")
		if (painterState is AsyncImagePainter.State.Loading) {
			CircularProgressIndicator()
		}
	}
}