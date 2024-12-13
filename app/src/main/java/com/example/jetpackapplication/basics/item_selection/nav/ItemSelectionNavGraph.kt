package com.example.jetpackapplication.basics.item_selection.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapplication.basics.item_selection.data.DemoItemSelection
import com.example.jetpackapplication.basics.item_selection.data.GenericParcelableArrayListNavType
import com.example.jetpackapplication.basics.item_selection.repo.DemoItemRepo
import com.example.jetpackapplication.basics.item_selection.ui.ItemSelectionDashScreenContent
import com.example.jetpackapplication.basics.item_selection.ui.ItemSelectionDetailsScreenContent
import com.example.jetpackapplication.basics.item_selection.viewModel.ItemSelectionListingViewModel
import com.example.jetpackapplication.basics.item_selection.viewModel.ItemSelectionViewModelFactory
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf


@Composable
fun SetupItemSelectionNavGraph() {
	val navController : NavHostController = rememberNavController()
	val demoItemRepo = remember {
		DemoItemRepo()
	}
	val viewModel : ItemSelectionListingViewModel = viewModel(
		factory =
		ItemSelectionViewModelFactory(demoItemRepo)
	)

	val navType = GenericParcelableArrayListNavType.create(DemoItemSelection.serializer())


	NavHost(
		navController = navController ,
		startDestination = ItemSelectionScreens.DashScreen
	) {
		composable<ItemSelectionScreens.DashScreen>(
		) {
			ItemSelectionDashScreenContent(navController , viewModel)
		}

		composable<ItemSelectionScreens.DetailsScreen>(
			typeMap = mapOf(typeOf<List<DemoItemSelection>>() to navType)		) {
//
			navBackStackEntry ->
			val itemList = navBackStackEntry.arguments?.getString("itemList")
			val demoItems = itemList?.let { Json.decodeFromString<List<DemoItemSelection>>(it) } ?: emptyList()
//			val details : ItemSelectionScreens.DetailsScreen = it
////				.toRoute<ItemSelectionScreens.DetailsScreen>()
			ItemSelectionDetailsScreenContent(demoItems,viewModel)
			//ItemSelectionDetailsScreenContent(viewModel,details.itemSelection,demoItems)

		}
	}
}
