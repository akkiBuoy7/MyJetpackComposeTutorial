package com.example.jetpackapplication.basics.item_selection.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackapplication.basics.item_selection.data.DemoItemSelection
import com.example.jetpackapplication.basics.item_selection.repo.DemoItemRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class ItemSelectionListingViewModel(private val demoItemRepo : DemoItemRepo) :
		ViewModel() {
	private val _demoItems : MutableStateFlow<List<DemoItemSelection>>
		get() = demoItemRepo.demoItems
	val demoItems: StateFlow<List<DemoItemSelection>> = _demoItems

	fun getSelectedItems() = _demoItems.value.filter { it.isSelected }

	init {
		viewModelScope.launch {
			demoItemRepo.getDemoItems()
		}
	}
// Toggle selection for the given item
	fun toggleSelection(index: Int) {
		_demoItems.value = _demoItems.value.mapIndexed { i, item ->
			if (i == index) {
				item.copy(isSelected = !item.isSelected)
			} else {
				item
			}
		}
	}
}