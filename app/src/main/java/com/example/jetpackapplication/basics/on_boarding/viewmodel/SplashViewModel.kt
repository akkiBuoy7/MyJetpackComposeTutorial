package com.example.jetpackapplication.basics.on_boarding.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackapplication.basics.on_boarding.boarding_screens.BoardingScreen
import com.example.jetpackapplication.basics.on_boarding.data.DataStoreRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
	private val repository : DataStoreRepository
) : ViewModel() {

	private val _isLoading : MutableState<Boolean> = mutableStateOf(true)
	val isLoading : State<Boolean> = _isLoading

	private val _startDestination : MutableState<Any> =
		mutableStateOf(BoardingScreen.Welcome)
	val startDestination : State<Any> = _startDestination

	init {
		viewModelScope.launch {
			repository.readOnBoardingState().collect { completed ->
				if (completed) {
					_startDestination.value = BoardingScreen.Home
				} else {
					_startDestination.value = BoardingScreen.Welcome
				}
			}
			_isLoading.value = false
		}
	}

}