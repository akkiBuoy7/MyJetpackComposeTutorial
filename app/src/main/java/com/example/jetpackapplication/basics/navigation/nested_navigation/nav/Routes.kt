package com.example.jetpackapplication.basics.navigation.nested_navigation.nav

const val ROOT_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"
const val DASH_GRAPH_ROUTE = "dash"

sealed class Routes(val route:String){
	data object Signup : Routes("signup_screen")
	data object Login : Routes("login_screen")
	data object Dash : Routes("dash_screen")
	data object Main : Routes("main_screen")
}