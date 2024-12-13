package com.example.jetpackapplication.basics.item_selection.ui

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.jetpackapplication.basics.item_selection.data.DemoItemSelection
import com.example.jetpackapplication.basics.item_selection.viewModel.ItemSelectionListingViewModel

@Composable
fun ItemSelectionDetailsScreenContent(
	list : List<DemoItemSelection> ,
	viewModel : ItemSelectionListingViewModel ,
) {

	val selectedItems : List<DemoItemSelection> = viewModel.getSelectedItems()
	Log.d("RECEIVED" , "ItemSelectionDetailsScreenContent: ${list.size}")

	LazyColumn {
		items(selectedItems) { item ->
			Text(text = item.role)
		}
	}

}