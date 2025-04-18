package com.example.jetpackapplication.basics.layouts.nested_scroll.custom_scroll_state

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Constraints
import kotlin.math.roundToInt

@Composable
fun VerticalNestedScrollView(
	modifier : Modifier = Modifier ,
	state : NestedScrollViewState ,
	header : @Composable () -> Unit = {} ,
	horizontalContent : @Composable () -> Unit = {} ,
	verticalContent : @Composable () -> Unit = {}
) {
	NestedScrollView(
		modifier = modifier ,
		state = state ,
		orientation = Orientation.Vertical ,
		header = header ,
		horizontalContent = horizontalContent ,
		verticalContent = verticalContent
	)
}

@Composable
fun NestedScrollView(
	modifier : Modifier = Modifier ,
	state : NestedScrollViewState ,
	orientation : Orientation ,
	header : @Composable () -> Unit = {} ,
	horizontalContent : @Composable () -> Unit = {} ,
	verticalContent : @Composable () -> Unit = {}
) {
	Layout(
		modifier = modifier
			.scrollable(
				orientation = orientation ,
				state = rememberScrollableState {
					state.drag(it)
				}
			)
			.nestedScroll(state.nestedScrollConnectionHolder) ,
		content = {
			Box {
				header.invoke()
			}
			Box {
				horizontalContent.invoke()
			}
			Box {
				verticalContent.invoke()
			}
		}
	) { measurables , constraints ->
		layout(constraints.maxWidth , constraints.maxHeight) {
			when (orientation) {
				Orientation.Vertical -> {
					val headerPlaceable =
						measurables[0].measure(constraints.copy(maxHeight = Constraints.Infinity))
					headerPlaceable.place(0 , state.offset.roundToInt())
					state.updateBounds(-(headerPlaceable.height.toFloat()))
					val contentPlaceable =
						measurables[1].measure(constraints.copy(maxHeight = constraints.maxHeight))
					contentPlaceable.place(
						0 ,
						state.offset.roundToInt() + headerPlaceable.height
					)
					val contentPlaceable1 =
						measurables[2].measure(constraints.copy(maxHeight = constraints
							.maxHeight))
					contentPlaceable1.place(
						0 ,
						state.offset.roundToInt() + headerPlaceable.height +
								contentPlaceable.height
					)
				}

				Orientation.Horizontal -> {}
			}
		}
	}
}








