package com.example.jetpackapplication.basics.navigation.basic

const val DETAIL_ARG_KEY = "id"
const val DETAIL_ARG_KEY_2 = "name"

const val NEXT_ARG_KEY = "no"
const val NEXT_ARG_KEY_2 = "value"

sealed class Screens(val route : String) {
	data object Home : Screens(route = "home_screen")
	data object Details : Screens(
		route =
		"details_screen/{$DETAIL_ARG_KEY}/{$DETAIL_ARG_KEY_2}"
	) {
//		fun passId(id:Int):String{
////			return "details_screen/$id"
//			return this.route.replace(oldValue = "{$DETAIL_ARG_KEY}", newValue = id.toString())
//		}

		fun passIdName(id : Int , name : String) : String {
			return this.route.replace(
				oldValue =
				"{$DETAIL_ARG_KEY}/{$DETAIL_ARG_KEY_2}" , newValue
				= "$id/$name"
			)
		}
	}

	data object Next : Screens(
		route =
		"next_screen?no={no}&value={value}"
	) {
		fun passNoValue(no : Int =0, value : String="") : String {
			return "next_screen?no=$no&value=$value";
			//return "next_screen?$NEXT_ARG_KEY =$no&$NEXT_ARG_KEY_2=$value"
		}
	}
}