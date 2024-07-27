package com.example.jetpackapplication.basics.widgets.lazy_column

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomItem(model : Person , onClick : (obj : Person) -> Unit) {

	Row(
		modifier = Modifier
			.background(Color.LightGray)
			.fillMaxWidth()
			.padding(24.dp)
			.clickable {
				onClick(model)
			} ,
		verticalAlignment = Alignment.Bottom ,
		horizontalArrangement = Arrangement.spacedBy(20.dp)
	) {
		Text(text = "${model.age}")
		Text(text = model.firstName)
		Text(text = model.lastName)
	}

}

@Preview
@Composable
private fun CustomItemPreview() {
	CustomItem(model = Person(1 , "Akash" , "Saha" , 32) , onClick = {})
}