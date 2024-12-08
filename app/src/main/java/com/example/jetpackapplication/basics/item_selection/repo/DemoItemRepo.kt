package com.example.jetpackapplication.basics.item_selection.repo

import com.example.jetpackapplication.R
import com.example.jetpackapplication.basics.item_selection.data.DemoItemSelection
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DemoItemRepo {

	private val _demoItems = MutableStateFlow<List<DemoItemSelection>>(emptyList())
	val demoItems : MutableStateFlow<List<DemoItemSelection>>
		get() = _demoItems

	 suspend fun getDemoItems() {
		//delay(3000)
		val list = mutableListOf<DemoItemSelection>()
		list.add(
			DemoItemSelection(
				role = "Software Developer" ,
				desc = "Develops software" ,
				img = R.drawable.user
			)
		)
		list.add(
			DemoItemSelection(
				role = "AI & ML" , desc = "Builds models" , img = R.drawable.user1
			)
		)
		list.add(
			DemoItemSelection(
				role = "Data Analyst" , desc = "Analyze data" , img = R.drawable.user2
			)
		)
		list.add(
			DemoItemSelection(
				role = "Manager" , desc = "Manages people" , img = R.drawable.user3
			)
		)
		list.add(
			DemoItemSelection(
				role = "Software Developer" ,
				desc = "Develops software" ,
				img = R.drawable.user
			)
		)
		list.add(
			DemoItemSelection(
				role = "AI & ML" , desc = "Builds models" , img = R.drawable.user1
			)
		)
		list.add(
			DemoItemSelection(
				role = "Data Analyst" , desc = "Analyze data" , img = R.drawable.user2
			)
		)
		list.add(
			DemoItemSelection(
				role = "Manager" , desc = "Manages people" , img = R.drawable.user3
			)
		)
		list.add(
			DemoItemSelection(
				role = "Software Developer" ,
				desc = "Develops software" ,
				img = R.drawable.user
			)
		)
		list.add(
			DemoItemSelection(
				role = "AI & ML" , desc = "Builds models" , img = R.drawable.user1
			)
		)
		list.add(
			DemoItemSelection(
				role = "Data Analyst" , desc = "Analyze data" , img = R.drawable.user2
			)
		)
		list.add(
			DemoItemSelection(
				role = "Manager" , desc = "Manages people" , img = R.drawable.user3
			)
		)
		list.add(
			DemoItemSelection(
				role = "Software Developer" ,
				desc = "Develops software" ,
				img = R.drawable.user
			)
		)
		list.add(
			DemoItemSelection(
				role = "AI & ML" , desc = "Builds models" , img = R.drawable.user1
			)
		)
		list.add(
			DemoItemSelection(
				role = "Data Analyst" , desc = "Analyze data" , img = R.drawable.user2
			)
		)
		list.add(
			DemoItemSelection(
				role = "Manager" , desc = "Manages people" , img = R.drawable.user3
			)
		)

		if (list.isNotEmpty()) {
			_demoItems.emit(list)
		}
	}
}
