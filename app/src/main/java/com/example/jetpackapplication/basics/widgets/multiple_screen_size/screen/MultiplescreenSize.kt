import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jetpackapplication.basics.widgets.multiple_screen_size.screen.CustomData
import com.example.jetpackapplication.basics.widgets.multiple_screen_size.screen.MultipleScreensizeViewmodel
import com.example.jetpackapplication.basics.widgets.multiple_screen_size.util.WindowSize
import com.example.jetpackapplication.basics.widgets.multiple_screen_size.util.WindowType

@Composable
fun MultipleScreenSizeScreen(
	windowSize : WindowSize ,
	homeViewModel : MultipleScreensizeViewmodel = viewModel()
) {
	val items = homeViewModel.items

	LazyColumn(
		contentPadding = PaddingValues(12.dp) ,
		verticalArrangement = Arrangement.spacedBy(12.dp)
	) {
		items(items = items , key = { it.id }) {
			AdaptableItem(data = it , windowSize = windowSize)
		}
	}
}

@Composable
fun AdaptableItem(data : CustomData , windowSize : WindowSize) {
	Log.d("WindowSize" , "AdaptableItem: ${windowSize}")
	// show maxlines based on window width
	val maxLines by remember(key1 = windowSize) {
		mutableStateOf(if (windowSize.width == WindowType.Compact) 4 else 10)
	}
	// based on window height show column or row content
	// if tablet show Column
	when (windowSize.height) {
		WindowType.Expanded -> {
			Column {
				ColumnContent(
					data = data ,
					windowSize = windowSize ,
					maxLines = maxLines
				)
			}
		}
		WindowType.Medium -> {
			Column {
				ColumnContent(
					data = data ,
					windowSize = windowSize ,
					maxLines = maxLines
				)
			}
		}
		// if mobile show row
		else -> {
			Row(
				modifier = Modifier.fillMaxWidth() ,
				horizontalArrangement = Arrangement.spacedBy(12.dp)
			) {
				RowContent(
					data = data ,
					windowSize = windowSize ,
					maxLines = maxLines
				)
			}
		}
	}
}

@Composable
fun RowScope.RowContent(
	data : CustomData ,
	windowSize : WindowSize ,
	maxLines : Int
) {
	// based on window height show icons
	// if tablet show icons
	val showIcons by remember(key1 = windowSize) {
		mutableStateOf(windowSize.height == WindowType.Medium)
	}

	AsyncImage(
		modifier = Modifier
			.weight(1f) ,
		model = ImageRequest.Builder(LocalContext.current)
			.data(data = data.image)
			.crossfade(enable = true)
			.build() ,
		contentDescription = "Image" ,
		contentScale = ContentScale.Crop
	)

	Column(modifier = Modifier.weight(1f)) {
		Text(
			text = data.title ,
			maxLines = 1 ,
			overflow = TextOverflow.Ellipsis ,
			style = TextStyle(
				fontSize =
				// based on window width change font size
				when (windowSize.width) {
					WindowType.Expanded -> MaterialTheme.typography.h2.fontSize
					WindowType.Medium -> MaterialTheme.typography.h5.fontSize
					else -> MaterialTheme.typography.h6.fontSize
				} ,
				fontWeight = FontWeight.Bold
			)
		)
		Spacer(modifier = Modifier.height(6.dp))
		Text(
			modifier = Modifier.alpha(ContentAlpha.disabled) ,
			text = data.description ,
			maxLines = maxLines ,
			overflow = TextOverflow.Ellipsis ,
			style = TextStyle(
				fontSize =
				// based on window width change font size
				when (windowSize.width) {
					WindowType.Expanded -> MaterialTheme.typography.h5.fontSize
					WindowType.Medium -> MaterialTheme.typography.h6.fontSize
					else -> MaterialTheme.typography.body1.fontSize
				}
			)
		)
		if (showIcons) {
			Spacer(modifier = Modifier.height(12.dp))
			Row(
				modifier = Modifier.fillMaxWidth() ,
				horizontalArrangement = Arrangement.spacedBy(6.dp)
			) {
				data.icons.forEach {
					Icon(
						modifier = Modifier.size(40.dp) ,
						imageVector = it ,
						contentDescription = "Icon"
					)
				}
			}
		}
	}
}

@Composable
fun ColumnContent(
	data : CustomData ,
	windowSize : WindowSize ,
	maxLines : Int
) {

	AsyncImage(
		modifier = Modifier
			.fillMaxWidth()
			.height(400.dp) ,
		model = ImageRequest.Builder(LocalContext.current)
			.data(data = data.image)
			.crossfade(enable = true)
			.build() ,
		contentDescription = "Image" ,
		contentScale = ContentScale.Crop
	)

	Column {
		Text(
			text = data.title ,
			maxLines = 1 ,
			overflow = TextOverflow.Ellipsis ,
			style = TextStyle(
				fontSize =
				when (windowSize.height) {
					WindowType.Expanded -> MaterialTheme.typography.h3.fontSize
					else -> MaterialTheme.typography.h6.fontSize
				} ,
				fontWeight = FontWeight.Bold
			)
		)
		Spacer(modifier = Modifier.height(6.dp))
		Text(
			modifier = Modifier.alpha(ContentAlpha.disabled) ,
			text = data.description ,
			maxLines = maxLines ,
			overflow = TextOverflow.Ellipsis ,
			style = TextStyle(
				fontSize =
				when (windowSize.height) {
					WindowType.Expanded -> MaterialTheme.typography.h5.fontSize
					else -> MaterialTheme.typography.body1.fontSize
				}
			)
		)

		Spacer(modifier = Modifier.height(12.dp))
		Row(
			modifier = Modifier.fillMaxWidth() ,
			horizontalArrangement = Arrangement.spacedBy(6.dp)
		) {
			data.icons.forEach {
				Icon(
					modifier = Modifier.size(40.dp) ,
					imageVector = it ,
					contentDescription = "Icon"
				)
			}
		}

	}
}