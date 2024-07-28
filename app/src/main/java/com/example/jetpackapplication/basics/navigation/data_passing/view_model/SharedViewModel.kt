package com.example.jetpackapplication.basics.navigation.data_passing.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

	var myModel by mutableStateOf<MyModel?>(null)
		private set

	fun addData(myModel1 : MyModel) {
		myModel = myModel1
	}
}