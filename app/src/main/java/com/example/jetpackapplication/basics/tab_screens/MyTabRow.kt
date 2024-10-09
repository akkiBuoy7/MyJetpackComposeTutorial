package com.example.jetpackapplication.basics.tab_screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetpackapplication.basics.tab_screens.tab_screens.FavouriteContent
import com.example.jetpackapplication.basics.tab_screens.tab_screens.ProfileContent
import com.example.jetpackapplication.basics.tab_screens.tab_screens.ShopContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class , ExperimentalMaterial3Api::class)
@Composable
fun MyTabRow() {
	val tabScreens = listOf(
		TabItem(
			unselectedIcon = Icons.Outlined.ShoppingCart ,
			selectedIcon = Icons.Filled.ShoppingCart ,
			text = "Shop" ,
			screen = ShopContent()
		) ,
		TabItem(
			unselectedIcon = Icons.Outlined.FavoriteBorder ,
			selectedIcon = Icons.Filled.Favorite ,
			text = "Favourite" ,
			screen = FavouriteContent()
		) ,
		TabItem(
			unselectedIcon = Icons.Outlined.Person ,
			selectedIcon = Icons.Filled.Person ,
			text = "Profile" ,
			screen = ProfileContent()
		)
	)
	val scope = rememberCoroutineScope()
	val pagerState = rememberPagerState(pageCount = { tabScreens.size })
	val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }



	Scaffold(
		//topBar = { TopAppBar(title = { Text(text = "Home") }) }
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(top = it.calculateTopPadding())
		) {
			TabRow(
				selectedTabIndex = selectedTabIndex.value ,
				modifier = Modifier.fillMaxWidth()
			) {
				tabScreens.forEachIndexed { index , currentTab ->
					Tab(
						selected = selectedTabIndex.value == index ,
						selectedContentColor = MaterialTheme.colorScheme.primary ,
						unselectedContentColor = MaterialTheme.colorScheme.outline ,
						onClick = {
							scope.launch {
								pagerState.animateScrollToPage(index)
							}
						} ,
						text = { Text(text = currentTab.text) } ,
						icon = {
							Icon(
								imageVector = if (selectedTabIndex.value == index)
									currentTab.selectedIcon else currentTab.unselectedIcon ,
								contentDescription = "Tab Icon"
							)
						}
					)
				}
			}

			HorizontalPager(
				state = pagerState ,
				modifier = Modifier
					.fillMaxWidth()
					.weight(1f)
			) {
				Box(
					modifier = Modifier.fillMaxSize() ,
					contentAlignment = Alignment.Center
				) {
					TabFeeder(int = selectedTabIndex.value)
				}
			}
		}
	}
}

data class TabItem(
	val selectedIcon : ImageVector ,
	val unselectedIcon : ImageVector ,
	val text : String ,
	val screen : Unit
)

@Composable
fun TabFeeder(int : Int) {
	when (int) {
		0 -> {
			return ShopContent()
		}

		1 -> {
			return FavouriteContent()
		}

		2 -> {
			return ProfileContent()
		}
	}
}
