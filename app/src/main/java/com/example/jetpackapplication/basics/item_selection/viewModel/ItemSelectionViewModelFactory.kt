package com.example.jetpackapplication.basics.item_selection.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackapplication.basics.item_selection.repo.DemoItemRepo

@Suppress("UNCHECKED_CAST")
class ItemSelectionViewModelFactory(private val demoItemRepo : DemoItemRepo) :
		ViewModelProvider.Factory {

	override fun <T : ViewModel> create(modelClass : Class<T>) : T {
		if (modelClass.isAssignableFrom(ItemSelectionListingViewModel::class.java)){
			return  ItemSelectionListingViewModel(demoItemRepo) as T
		}
		throw IllegalArgumentException("Unknown viewModel class")
	}
}