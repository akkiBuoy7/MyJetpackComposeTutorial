package com.example.jetpackapplication.basics.widgets.lazy_column

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun MyLazyColumn() {

	val repo = PersonRepository()
	val data = repo.getAllData()

	LazyColumn(
		contentPadding = PaddingValues(all = 20.dp) ,
		verticalArrangement = Arrangement.spacedBy(20.dp)
	) {

//		items(items = data) { obj ->
//			CustomItem(model = obj)
//		}
		itemsIndexed(
			items = data ,
			key = { index , person ->
				person.id
			}
		) { index , person ->

//			CustomItem(model = person) {
//				Log.d("MyLazyColumn" , "OnClick: ${it.firstName} index : $index")
//			}

		}

	}

}