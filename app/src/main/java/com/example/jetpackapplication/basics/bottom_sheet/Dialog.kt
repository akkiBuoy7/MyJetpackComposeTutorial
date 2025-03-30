package com.example.jetpackapplication.basics.bottom_sheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.wear.compose.material.Text
import com.example.jetpackapplication.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyModalBottomSheet() {
	// need to use this bool as by default sheet is expanded
	var isSheetOpen by remember {
		mutableStateOf(false)
	}
	val sheetState = rememberModalBottomSheetState()
	Box(
		modifier = Modifier.fillMaxSize() ,
		contentAlignment = Alignment.Center
	) {
		Button(onClick = { isSheetOpen = true }) {
			Text(text = "Open modal sheet")
		}
	}
	if (isSheetOpen) {
		ModalBottomSheet(sheetState = sheetState , onDismissRequest = {
			isSheetOpen = false
		}) {
			Image(
				painter = painterResource(id = R.drawable.nature) ,
				contentDescription = "sheet image"
			)
		}
	}

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldBottomSheet() {
	val scaffoldSheetState = rememberBottomSheetScaffoldState()

	val scope = rememberCoroutineScope()

	BottomSheetScaffold(scaffoldState = scaffoldSheetState ,
	                    sheetContent = {
		                    Image(
			                    painter = painterResource(id = R.drawable.nature) ,
			                    contentDescription = "scaffold sheet image"
		                    )
	                    }) {
		Box(
			modifier = Modifier.fillMaxSize() ,
			contentAlignment = Alignment.Center
		) {

			Button(onClick = {
				scope.launch {
					scaffoldSheetState.bottomSheetState.expand()

				}
			}) {
				Text(text = "Open scaffold modal sheet")
			}
		}

	}
}