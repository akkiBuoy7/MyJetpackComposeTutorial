package com.example.jetpackapplication.basics.navigation.data_passing.view_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MyModel(
	val firstName : String ,
	val secondName : String
) : Parcelable