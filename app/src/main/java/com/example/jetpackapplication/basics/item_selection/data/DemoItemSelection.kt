package com.example.jetpackapplication.basics.item_selection.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class DemoItemSelection(
	val role : String , val desc : String , val img : Int , var
	isSelected : Boolean = false
) : Parcelable
