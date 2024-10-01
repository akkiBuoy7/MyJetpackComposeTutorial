package com.example.jetpackapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapplication.basics.bottom_navigation.basic.bottom_nav.MainScreen
import com.example.jetpackapplication.basics.bottom_navigation.nav_rail.navigation_rail_screen.Material3BottomRailNav
import com.example.jetpackapplication.basics.bottom_navigation.nested_bottom_navigation.nav.graphs.RootNavigationGraph
import com.example.jetpackapplication.basics.bottom_navigation.nested_bottom_navigation.screens.bottom_nav.Material3BottomNav
import com.example.jetpackapplication.basics.nav_drawer.SetupNavDrawer
import com.example.jetpackapplication.basics.nav_drawer.navigation.SetupNavDrawerGraph
import com.example.jetpackapplication.basics.widgets.HyperlinkText
import com.example.jetpackapplication.basics.widgets.TopAppBar
import com.example.jetpackapplication.basics.widgets.WebBrowser
import com.example.jetpackapplication.basics.widgets.lazy_column.LazyListStateContent
import com.example.jetpackapplication.ui.theme.JetpackApplicationTheme

const val TAG = "MyJetpack"

class MainActivity : ComponentActivity() {

	@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			//MyText()
			//MyTextSelection()
			//MySuperScriptText("Akash","Saha")

			//ListShimmer()

			//SetUpSplashNavGraph()
			//LoadingAnimationContent()

			//SwipableListContent()

			//SelectableItemContent()

			//LazyListStateContent()

			//TopAppBar()

			//Material3BottomNav()



			//WebBrowser()

//			HyperlinkText(
//				text = "By signing in, you agree to the privacy policy and terms of " +
//						"use." ,
//				linkText = listOf("privacy policy" , "terms of use") ,
//				hyperlinks = listOf("https://youtube.com" , "https://www.google.com") ,
//				fontSize = MaterialTheme.typography.bodyMedium.fontSize
//			)

			//SetupNavGraphForMultipleScreenSize()

			//SetupNavGraphBoxConstraint()

//			MyExpandableCard(title = "Title" , desc = stringResource(id = R.string
//			 .desc))
			//MyTextField()
//			MyGradientButton(
//				gradient = Brush.horizontalGradient(
//					colors = listOf
//						(GradientColor1 , GradientColor2)
//				) ,
//				text = "Button" , textColor = Color.White
//			)

			//MyLazyColumn()
			//MyStickyHeaderLazyColumn()
			//MyAnimatedLazyColumn()

			//SetupNavGraphForDataPassing()
			//SetupNavGraphForSerializationDataPassing()

			//RootNavigationGraph(navController = rememberNavController())
			//MainScreen()
			//SetUpNavGraph()
			//SetUpNestedNavGraph()
			//MyBox()
			//MyCoilImage()
			//App()
			//Counter()
			//BuildUi()
			//BuildScreen()
			//PreviewItemWithLazyColumn()
			// SayHello(txt = "Hello World!")
			//ShowTextInput()
			//LaunchedEffectComposable(Modifier.fillMaxSize())
			//CoroutineScopeComposable()
			//Splash()
			//ProducedStateComposable()
			//DerivedState()


			//SetupNavDrawer()

			val windowClass = calculateWindowSizeClass(this)
			val showNavigationRail =
				windowClass.widthSizeClass != WindowWidthSizeClass.Compact

			Material3BottomRailNav(navController = rememberNavController(),
			                       showNavigationRail = showNavigationRail)

		}
	}
}

@Composable
fun App() {
	val theme = remember {
		mutableStateOf(false)
	}
	JetpackApplicationTheme(theme.value) {
		Box(
			modifier = Modifier.fillMaxSize() ,
			contentAlignment = Alignment.Center ,
		) {
			Card(
				modifier = Modifier
					.height(200.dp)
					.width(200.dp)
					.align(Alignment.Center) ,
			) {
				Column(
					horizontalAlignment = Alignment.CenterHorizontally ,
					verticalArrangement = Arrangement.Center ,
					modifier = Modifier.background(MaterialTheme.colorScheme.background)
				) {
					Text(
						text = "HELLO WORLD!" ,
						style = MaterialTheme.typography.bodyMedium ,
						color = Color.White
					)
					Spacer(modifier = Modifier.padding(10.dp))
					Button(onClick = {

						theme.value = !theme.value

					}) {
						Text(
							text = "Change Theme" ,
							style = MaterialTheme.typography.bodyMedium
						)
					}
				}
			}

		}
	}
}

@Composable
fun Counter() {
	val data = remember {
		mutableStateOf(listOf<String>())
	}
	val counter = remember {
		mutableStateOf(3)
	}

	val key = counter.value % 3 == 0
//    var isToggled by remember {
//        mutableStateOf(false)
//    }

	Log.d(TAG , "Key value: $key")
	LaunchedEffect(key1 = key) {
		val fetchedData = fetchData()
		data.value = fetchedData
		Log.d(TAG , "Fetching List: ${data.toString()}")
	}

	Column(modifier = Modifier.fillMaxSize()) {
		LazyColumn(content = {
			Log.d(TAG , "Counter: ${data.toString()}")
			items(data.value) {
				Text(text = it)
			}
		})
		Button(onClick = {
			counter.value++
		}) {
			Text(text = "Fetch Data ${counter.value}")
		}
	}
}

suspend fun fetchData() : List<String> {
	return listOf("3" , "6" , "9" , "12" , "15" , "18")
}

//@Preview(showSystemUi = true)
//@Composable
//fun PreviewFunctionText() {
//    SayHello(txt = "Hello akash")
//}

//@Preview(showSystemUi = true)
//@Composable
//fun PreviewFunctionImage() {
//    ShowImage(param = R.drawable.india)
//}

//@Preview(showSystemUi = true)
//@Composable
//fun PreviewFunction() {
//    //ShowButton()
//    //ShowTextInput()
//    //ShowColumn()
//    //ShowRow()
//    //ShowBox()
//    //ShowColumnList()
//    ShowModifier()
//}

@Preview
@Composable
private fun MainPreview() {
	MainScreen()
}

@Composable
fun ShowModifier() {
	Image(
		painter = painterResource(id = R.drawable.india) ,
		contentDescription = "" ,
		alignment = Alignment.Center ,
		modifier = Modifier
			.width(300.dp)
			.height(300.dp)
			.padding(7.dp)
			.clip(CircleShape)
			.border(10.dp , Color.Red)
			.fillMaxSize()
	)
}

@Composable
fun ShowColumnList() {
	Column(
		verticalArrangement = Arrangement.Center ,
		modifier = Modifier.fillMaxSize() ,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		ListViewItem(
			name = "Akash0" ,
			image = R.drawable.user ,
			role = "Software Developer" ,
			modifier = Modifier.fillMaxSize()
		)
		ListViewItem(
			name = "Akash1" ,
			image = R.drawable.user ,
			role = "Software Developer"
		)
		ListViewItem(
			name = "Akash2" ,
			image = R.drawable.user ,
			role = "Software Developer"
		)
		ListViewItem(
			name = "Akash3" ,
			image = R.drawable.user ,
			role = "Software Developer"
		)
	}
}

@Composable
fun ListViewItem(
	name : String ,
	image : Int ,
	role : String ,
	modifier : Modifier = Modifier.fillMaxSize()
) {
	val painter = painterResource(id = image)
	Row(
		modifier = modifier ,
		horizontalArrangement = Arrangement.Center ,
		verticalAlignment = Alignment.CenterVertically
	) {
		Box() {
			Image(
				modifier = Modifier
					.size(60.dp)
					.padding(10.dp) ,
				painter = painter ,
				contentDescription = "user" ,
			)
		}
		Column(modifier = Modifier.padding(top = 10.dp)) {
			Text(text = name , fontWeight = FontWeight.Bold)
			Text(text = role , fontWeight = FontWeight.Light)
		}
	}
}

@Composable
fun ShowBox() {
	Box(
		contentAlignment = Alignment.Center , modifier = Modifier.fillMaxSize()
	) {
		Image(
			painter = painterResource(id = R.drawable.heart) ,
			contentDescription = "heart"
		)
		Image(
			painter = painterResource(id = R.drawable.up_arrow) ,
			contentDescription = "heart"
		)
	}
}

@Composable
fun ShowColumn() {
	Column(
		verticalArrangement = Arrangement.Center ,
		modifier = Modifier.fillMaxSize() ,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(text = "A" , fontWeight = FontWeight.Bold , fontSize = 24.sp)
		Text(text = "B" , fontWeight = FontWeight.Bold , fontSize = 24.sp)
	}
}

@Composable
fun ShowRow() {
	Row(
		horizontalArrangement = Arrangement.Center ,
		modifier = Modifier.fillMaxSize() ,
		verticalAlignment = Alignment.CenterVertically
	) {
		Text(text = "A" , fontWeight = FontWeight.Bold , fontSize = 24.sp)
		Text(text = "B" , fontWeight = FontWeight.Bold , fontSize = 24.sp)
	}
}

@Composable
fun ShowButton() {
	Button(
		onClick = { } ,
//		colors = ButtonDefaults.buttonColors(
//			contentColor = Color.Blue , backgroundColor = Color.Yellow
//		)
	) {
		Text(text = "click me")
		Image(
			painter = painterResource(id = R.drawable.india) ,
			contentDescription = "dummy"
		)
	}
}

@Composable
fun ShowImage(param : Int) {
	Image(
		painter = painterResource(id = param) ,
		contentDescription = "Dummy Image" ,
		contentScale = ContentScale.Fit
	)
}

@Composable
fun ShowTextInput() {
	val state = remember { mutableStateOf(value = "") }
	TextField(value = state.value , onValueChange = {
		state.value = it
		Log.d(TAG , "ShowTextInput: $it")
	} , label = { Text(text = "Enter") } , placeholder = { Text(text = "text") })

}

@Composable
fun SayHello(txt : String) {
	Text(text = txt , textAlign = TextAlign.Center)
}