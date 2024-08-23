package com.example.jetpackapplication

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.example.jetpackapplication.basics.permission.CameraPermissionTextProvider
import com.example.jetpackapplication.basics.permission.PermissionDialog
import com.example.jetpackapplication.basics.permission.PermissionViewModel
import com.example.jetpackapplication.basics.permission.PhoneCallPermissionTextProvider
import com.example.jetpackapplication.basics.permission.RecordAudioPermissionTextProvider
import com.example.jetpackapplication.ui.theme.JetpackApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PermissionActivity : ComponentActivity() {

	private val permissionsToRequest = arrayOf(
		Manifest.permission.RECORD_AUDIO ,
		Manifest.permission.CALL_PHONE ,
	)

	private val singlePermission = Manifest.permission.ACCESS_BACKGROUND_LOCATION

	@RequiresApi(Build.VERSION_CODES.Q)
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			JetpackApplicationTheme {
				var shouldDismissDialog1 by remember {
					mutableStateOf(true)
				}

				var shouldDismissDialog2 by remember {
					mutableStateOf(true)
				}
				val permissionViewModel : PermissionViewModel = hiltViewModel()
				val dialogQueue = permissionViewModel.visiblePermissionDialogQueue

				Log.d(TAG , "CHECK PERMISSIONS: ${dialogQueue.toString()}")
				val backgroundPermissionResult = rememberLauncherForActivityResult(
					contract =
					ActivityResultContracts.RequestPermission() ,
					onResult = { isGranted ->
						permissionViewModel.onPermissionResult(
							isGranted , Manifest.permission.ACCESS_BACKGROUND_LOCATION
						)
					}
				)
				val multiplePermissionResultLauncher = rememberLauncherForActivityResult(
					contract = ActivityResultContracts.RequestMultiplePermissions() ,
					onResult = { perms ->
						permissionsToRequest.forEach { permission ->
							Log.d(
								TAG ,
								"CHECK PERMISSIONS: $permission >> ${perms[permission] == true}"
							)
							permissionViewModel.onPermissionResult(
								permission = permission ,
								isGranted = perms[permission] == true
							)
						}
					}
				)

				Column(
					modifier = Modifier
						.fillMaxSize()
						.padding(15.dp) ,
					verticalArrangement = Arrangement.Center ,
					horizontalAlignment = Alignment.CenterHorizontally
				) {
//					Button(onClick = {
//						backgroundPermissionResult.launch(Manifest.permission.CAMERA)
//					} , modifier = Modifier.fillMaxWidth()) {
//						Text(text = "Request Permission")
//					}
					Spacer(modifier = Modifier.height(16.dp))
					Button(onClick = {
						multiplePermissionResultLauncher.launch(permissionsToRequest)
					} , modifier = Modifier.fillMaxWidth()) {
						Text(text = "Request multiple permission")
					}

				}

//				if (shouldDismissDialog1) {
//					PermissionDialog(
//						permissionTextProvider =
//						BackgroundPermissionTextProvider() ,
//
//						isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
//							singlePermission
//						) ,
//						onDismiss = {
//							shouldDismissDialog1 = false
//							permissionViewModel.dismissDialog()
//						} ,
//						onOkClick = {
//							permissionViewModel.dismissDialog()
//							multiplePermissionResultLauncher.launch(
//								arrayOf(singlePermission)
//							)
//						} ,
//						onGoToAppSettings = ::openAppSettings
//					)
//
//				}

				if (shouldDismissDialog2) {
					dialogQueue
						.reversed()
						.forEach { permission ->
							PermissionDialog(
								permissionTextProvider = when (permission) {
									Manifest.permission.CAMERA -> {
										CameraPermissionTextProvider()
									}

									Manifest.permission.RECORD_AUDIO -> {
										RecordAudioPermissionTextProvider()
									}

									Manifest.permission.CALL_PHONE -> {
										PhoneCallPermissionTextProvider()
									}

									else -> return@forEach
								} ,
								isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
									permission
								) ,
								onDismiss = {
									shouldDismissDialog2 = false
									permissionViewModel.dismissDialog()
								} ,
								onOkClick = {
									permissionViewModel.dismissDialog()
									multiplePermissionResultLauncher.launch(
										arrayOf(permission)
									)
								} ,
								onGoToAppSettings = ::openAppSettings
							)
						}
				}

			}
		}
	}

}

fun Activity.openAppSettings() {
	Intent(
		Settings.ACTION_APPLICATION_DETAILS_SETTINGS ,
		Uri.fromParts("package" , packageName , null)
	).also(::startActivity)
}