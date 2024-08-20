package com.example.jetpackapplication.basics.widgets.lazy_column_box_constraint.nav
import androidx.navigation.compose.composable
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapplication.basics.widgets.lazy_column_box_constraint.screen.HomeScreen

@Composable
fun SetupNavGraphBoxConstraint() {
	val navController = rememberNavController()
	NavHost(
		navController = navController,
		startDestination = ScreenBoxConst.Home.route
	) {
		composable(route = ScreenBoxConst.Home.route) {
			HomeScreen()
		}
	}
}