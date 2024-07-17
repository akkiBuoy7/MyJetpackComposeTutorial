package com.example.jetpackapplication.basics

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyTextField() {
	var text by remember {
		mutableStateOf("")
	}
	var readOnlyState by remember {
		mutableStateOf(true)
	}
	var enableState by remember {
		mutableStateOf(false)
	}

	var textO by remember {
		mutableStateOf("")
	}
	var readOnlyStateO by remember {
		mutableStateOf(true)
	}
	var enableStateO by remember {
		mutableStateOf(false)
	}
	Column(
		modifier = Modifier.fillMaxSize() ,
		verticalArrangement = Arrangement.Center ,
		horizontalAlignment = Alignment.CenterHorizontally
	) {

		TextField(value = text , onValueChange = { newText ->
			text = newText
		} , placeholder = {
			Text(text = "Type here...")
		} , label = {
			Text(text = "Email")
		} , leadingIcon = {
			Icon(
				imageVector = Icons.Filled.Email , contentDescription = "emailIcon"
			)
		} , trailingIcon = {
			IconButton(onClick = {
				readOnlyState = !readOnlyState
				enableState = !enableState
			}) {
				Icon(imageVector = Icons.Filled.Edit , contentDescription = "editIcon")
			}
		} ,
			//readOnly = readOnlyState,
			      enabled = enableState ,
			      modifier = Modifier.background(Color.Magenta) ,
			      colors = TextFieldDefaults.textFieldColors(
				      textColor = Color.White ,
				      leadingIconColor = Color.White ,
				      trailingIconColor = Color.Black ,
				      disabledTextColor = Color.Yellow ,
				      unfocusedLabelColor = Color.Yellow ,
				      focusedLabelColor = Color.White
			      ) ,
			      keyboardOptions = KeyboardOptions(
				      keyboardType = KeyboardType.Email ,
				      imeAction = ImeAction.Done
			      ) ,
			      keyboardActions = KeyboardActions(
				      onDone = {
					      Log.d("Done IME" , "Done Clicked")
					      readOnlyState = !readOnlyState
					      enableState = !enableState
				      }
			      ))

		OutlinedTextField(value = textO , onValueChange = { newText ->
			textO = newText
		} , placeholder = {
			Text(text = "Type here...")
		} , label = {
			Text(text = "Email")
		} , leadingIcon = {
			Icon(
				imageVector = Icons.Filled.Email , contentDescription = "emailIcon"
			)
		} , trailingIcon = {
			IconButton(onClick = {
				readOnlyStateO = !readOnlyStateO
				enableStateO = !enableStateO
			}) {
				Icon(imageVector = Icons.Filled.Edit , contentDescription = "editIcon")
			}
		} ,
		                  readOnly = readOnlyStateO ,
			//enabled = enableStateO,
			              modifier = Modifier.background(Color.LightGray) ,
			              colors = TextFieldDefaults.textFieldColors(
				              textColor = Color.White ,
				              leadingIconColor = Color.White ,
				              trailingIconColor = Color.Black ,
				              disabledTextColor = Color.Yellow ,
				              unfocusedLabelColor = Color.Yellow ,
				              focusedLabelColor = Color.White
			              ) ,
			              keyboardOptions = KeyboardOptions(
				              keyboardType = KeyboardType.Email ,
				              imeAction = ImeAction.Done
			              ) ,
			              keyboardActions = KeyboardActions(
				              onDone = {
					              Log.d("Done IME" , "Done Clicked")
					              readOnlyStateO = !readOnlyStateO
					              enableStateO = !enableStateO
				              }
			              ))

	}

}

@Preview
@Composable
private fun PreviewTextField() {
	MyTextField()
}