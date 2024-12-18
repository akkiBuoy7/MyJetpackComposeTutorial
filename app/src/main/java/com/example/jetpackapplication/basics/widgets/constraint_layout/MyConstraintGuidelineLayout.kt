package com.example.jetpackapplication.basics.widgets.constraint_layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpackapplication.R
import com.example.jetpackapplication.basics.widgets.constraint_layout.data.CountryInfo

@Composable
fun CountryCardWithGuidelineConstraintLayout() {

	val indiaInfo = CountryInfo(
		R.drawable.india ,
		"India" ,
		"New Delhi" ,
		"Republic of India" ,
		"Asia" , "South Asia" ,
		"₹" ,
		"Indian Rupee" ,
		"+91" ,
		".in"
	)

	ConstraintLayout(
		modifier = Modifier
			.wrapContentHeight()
			.fillMaxWidth()
	) {
		val (flag , commonName , capital , officialName , region , subregion , currencySymbol , currencyName , mobileCode , tld) = createRefs()
		val imageResId = indiaInfo.flagId // Replace with your PNG image resource ID
		val imagePainter : Painter = painterResource(id = imageResId)

		val startGuideline = createGuidelineFromStart(2.dp)
		val topGuidLine = createGuidelineFromTop(2.dp)
		val bottomGuideline = createGuidelineFromBottom(5.dp)

		Image(painter = imagePainter ,
		      contentDescription = "Country Flag" ,
		      contentScale = ContentScale.Crop ,
		      modifier = Modifier
			      .fillMaxWidth(0.3f)
			      .size(80.dp)
			      .height(50.dp)
			      .padding(2.dp)
			      .constrainAs(flag) {
				      top.linkTo(topGuidLine)
				      start.linkTo(startGuideline)
			      })


		Text(
			text = indiaInfo.commonName ,
			modifier = Modifier
				.padding(2.dp)
				.constrainAs(commonName) {
					top.linkTo(flag.bottom)
					start.linkTo(startGuideline)
					end.linkTo(flag.end)
				} ,
			fontFamily = FontFamily.SansSerif ,
			textAlign = TextAlign.Center ,
			fontSize = 20.sp
		)

		Text(text = indiaInfo.nationalCapital ,
		     fontSize = 12.sp ,
		     textAlign = TextAlign.Left ,
		     modifier = Modifier
			     .padding(2.dp)
			     .constrainAs(capital) {
				     start.linkTo(startGuideline)
				     top.linkTo(commonName.bottom)
				     end.linkTo(flag.end)
				     bottom.linkTo(bottomGuideline)
			     })

		Text(
			text = "Republic of India" ,
			fontSize = 20.sp ,
			textAlign = TextAlign.Center ,
			modifier = Modifier
				.padding(2.dp)
				.fillMaxWidth(0.6f).constrainAs(
					officialName
				){
					start.linkTo(flag.end)
					top.linkTo(topGuidLine)
				}
		)

		Text(
			text = "Asia" ,
			fontSize = 15.sp ,
			textAlign = TextAlign.Center ,
			modifier = Modifier
				.padding(start = 90.dp)
				.fillMaxWidth(1f)
				.constrainAs(region){
					top.linkTo(officialName.bottom)
					bottom.linkTo(flag.bottom)

				}
		)

		Text(
			text = "South Asia" ,
			fontSize = 15.sp ,
			textAlign = TextAlign.Center ,
			modifier = Modifier
				.padding(start = 90.dp)
				.fillMaxWidth(1.0f)
				.constrainAs(
					subregion
				){
					top.linkTo(region.bottom)
				}
		)

		Box(
			modifier = Modifier
				.padding(top=20.dp).size(70.dp)
				.fillMaxWidth(1f)
				.constrainAs(currencySymbol){
					top.linkTo(subregion.bottom)
					start.linkTo(flag.end)
				}
		) {
			val imageResId = R.drawable.india // Replace with your PNG image
			// resource ID
			val imagePainter : Painter = painterResource(id = imageResId)
			Image(
				painter = imagePainter ,
				contentDescription = "Country Flag"
			)
		}

		Text(
			text = "Indian Rupee" ,
			fontSize = 19.sp ,
			textAlign = TextAlign.Center ,
			modifier = Modifier
				.padding(top = 20.dp, start = 10.dp)
				.fillMaxWidth(0.2f)
				.constrainAs(currencyName){
					top.linkTo(subregion.bottom)
					start.linkTo(currencySymbol.end)
				}
		)

		Text(
			text = "+91" ,
			textAlign = TextAlign.Center ,
			modifier = Modifier
				.fillMaxWidth(0.1f)
				.padding(top = 20.dp)
				.constrainAs(mobileCode){
					top.linkTo(subregion.bottom)
					start.linkTo(currencyName.end)
				}
		)

		Text(
			text = ".in" ,
			textAlign = TextAlign.Center ,
			modifier = Modifier
				.fillMaxWidth(0.1f)
				.constrainAs(tld){
					top.linkTo(mobileCode.bottom)
					start.linkTo(currencyName.end)
				}
		)


	}
}

@Preview
@Composable
private fun CountryCardWithGuidelineConstraintLayoutPrev() {
	CountryCardWithGuidelineConstraintLayout()
}