package com.example.jetpackapplication.basics.item_selection.data

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass

class GenericParcelableObjectNavType<T : Parcelable>(
	private val clazz: KClass<T> ,
	private val serializer: KSerializer<T>
) : NavType<T>(isNullableAllowed = false) {

	override fun put(bundle: Bundle , key: String , value: T) {
		bundle.putParcelable(key, value)
	}

	override fun get(bundle: Bundle , key: String): T? {
		return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			bundle.getParcelable(key, clazz.java)
		} else {
			@Suppress("DEPRECATION")
			bundle.getParcelable(key)
		}
	}

	override fun parseValue(value: String): T {
		return Json.decodeFromString(serializer , value)
	}

	override fun serializeAsValue(value: T): String {
		return Json.encodeToString(serializer , value)
	}
}

class GenericParcelableArrayListNavType< T : Parcelable>(serializer : KSerializer<T>) : NavType<ArrayList<T>>(isNullableAllowed = false) {
	override fun put(bundle: Bundle, key: String, value: ArrayList<T>) {
		val json = Json.encodeToString(value)
		bundle.putString(key, json)
	}

	override fun get(bundle: Bundle, key: String): ArrayList<T>? {
		val json = bundle.getString(key)
		return json?.let { Json.decodeFromString(it) }
	}

	override fun parseValue(value: String): ArrayList<T> {
		return Json.decodeFromString(value)
	}

	override fun serializeAsValue(value: ArrayList<T>): String {
		return Json.encodeToString(value)
	}

	companion object {
		inline fun <reified T : Parcelable> create(serializer: KSerializer<T>): GenericParcelableArrayListNavType<T> {
			return GenericParcelableArrayListNavType(serializer)
		}
	}
}


