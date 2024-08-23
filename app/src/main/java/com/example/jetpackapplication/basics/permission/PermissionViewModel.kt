package com.example.jetpackapplication.basics.permission

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PermissionViewModel  @Inject constructor() : ViewModel(){

	var visiblePermissionDialogQueue = mutableStateListOf<String>()


	fun dismissDialog(){
		if (Build.VERSION.SDK_INT >= 35) {
			visiblePermissionDialogQueue.removeFirst()
		}
	}

	@RequiresApi(35)
	fun onPermissionResult(isGranted:Boolean , permission : String){
		if(!isGranted){
			visiblePermissionDialogQueue.add(permission)
		}
	}
}