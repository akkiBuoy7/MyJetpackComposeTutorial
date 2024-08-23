package com.example.jetpackapplication.basics.permission

import android.util.Log
import androidx.compose.material.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Text

@Composable
fun PermissionDialog(
	isPermanentlyDeclined : Boolean ,
	permissionTextProvider : PermissionTextProvider ,
	onDismiss : () -> Unit ,
	onOkClick : () -> Unit ,
	onGoToAppSettings : () -> Unit ,
	modifier : Modifier = Modifier ,
) {
	AlertDialog(onDismissRequest = onDismiss ,
	            confirmButton = {
		            TextButton(onClick = { if (isPermanentlyDeclined) {
			            onGoToAppSettings()
		            } else {
			            onOkClick()
		            } }) {
			            Text(text = "Grant permission" , color = Color.Red)
		            }
	            } ,
//	            dismissButton = {
//		            TextButton(onClick = { }) {
//			            Text(text = "Dismiss" , color = Color.LightGray)
//		            }
//	            } ,
	            title = { Text(text = "Select") } ,
	            text = {
		            Text(
			            text = permissionTextProvider.getDescription(
				            isPermanentlyDeclined
			            )
		            )
	            } ,
	            backgroundColor = Color.Gray ,
	            contentColor = Color.White ,
	            modifier = modifier
	)

}

interface PermissionTextProvider {

	fun getDescription(isPermanentlyDeclined : Boolean) : String
}

class CameraPermissionTextProvider : PermissionTextProvider {

	override fun getDescription(isPermanentlyDeclined : Boolean) : String {
		return if (isPermanentlyDeclined) {
			"It seems you permanently declined camera permission. " +
					"You can go to the app settings to grant it."
		} else {
			"This app needs access to your camera so that your friends " +
					"can see you in a call."
		}
	}
}

class RecordAudioPermissionTextProvider : PermissionTextProvider {

	override fun getDescription(isPermanentlyDeclined : Boolean) : String {
		return if (isPermanentlyDeclined) {
			"It seems you permanently declined microphone permission. " +
					"You can go to the app settings to grant it."
		} else {
			"This app needs access to your microphone so that your friends " +
					"can hear you in a call."
		}
	}
}

class PhoneCallPermissionTextProvider : PermissionTextProvider {

	override fun getDescription(isPermanentlyDeclined : Boolean) : String {
		return if (isPermanentlyDeclined) {
			"It seems you permanently declined phone calling permission. " +
					"You can go to the app settings to grant it."
		} else {
			"This app needs phone calling permission so that you can talk " +
					"to your friends."
		}
	}
}

class BackgroundPermissionTextProvider : PermissionTextProvider {

	override fun getDescription(isPermanentlyDeclined : Boolean) : String {
		return if (isPermanentlyDeclined) {
			"It seems you permanently declined background access permission. " +
					"You can go to the app settings to grant it."
		} else {
			"This app needs background access permission so that you can talk " +
					"to your friends."
		}
	}
}