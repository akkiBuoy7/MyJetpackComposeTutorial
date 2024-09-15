package com.example.jetpackapplication.basics.nav_drawer

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapplication.basics.nav_drawer.navigation.SetupNavDrawerGraph
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SetupNavDrawer() {
	val navController = rememberNavController()
	val items = listOf(
		NavDrawerItem.All ,
		NavDrawerItem.Urgent ,
		NavDrawerItem.Settings ,
	)

	val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
	val scope = rememberCoroutineScope()
	var selectedItemIndex by rememberSaveable {
		mutableStateOf(0)
	}
	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentRoute = navBackStackEntry?.destination?.route
	ModalNavigationDrawer(
		drawerContent = {
			ModalDrawerSheet {
				Spacer(modifier = Modifier.height(16.dp))
				items.forEachIndexed { index , item ->
					NavigationDrawerItem(
						label = {
							Text(text = item.title)
						} ,
						selected = index == selectedItemIndex ,
						onClick = {

							selectedItemIndex = index

							navController.navigate(item.route) {
								navController.graph.startDestinationRoute?.let { route ->
									popUpTo(route) {
										saveState = true
									}
								}
								launchSingleTop = true
								restoreState = true
							}
							scope.launch {
								drawerState.close()
							}
						} ,
						icon = {
							Icon(
								imageVector = if (index == selectedItemIndex) {
									item.selectedIcon
								} else item.unselectedIcon ,
								contentDescription = item.title
							)
						} ,
						badge = {
							item.badgeCount?.let {
								Text(text = item.badgeCount.toString())
							}
						} ,
						modifier = Modifier
							.padding(NavigationDrawerItemDefaults.ItemPadding)
					)
				}
			}
		} ,
		drawerState = drawerState
	) {
		Scaffold(
			topBar = {
				TopAppBar(
					title = {
						Text(text = "Todo App")
					} ,
					navigationIcon = {
						IconButton(onClick = {
							scope.launch {
								drawerState.open()
							}
						}) {
							Icon(
								imageVector = Icons.Default.Menu ,
								contentDescription = "Menu"
							)
						}
					}
				)
			}
		) {
			SetupNavDrawerGraph(navController)
		}
	}
}
