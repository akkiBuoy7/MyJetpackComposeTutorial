package com.example.jetpackapplication.basics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackapplication.R

@Preview(showBackground = true , showSystemUi = true)
@Composable
fun TextPreview() {
	MyText()
}

@Composable
private fun MyText() {
	Column(
		verticalArrangement = Arrangement.SpaceEvenly , modifier = Modifier.fillMaxSize()
	) {
		SimpleText()
		TextUsingStyle()
		TextUsingAnnotations()
		RepeatText()
	}
}

@Composable
private fun SimpleText() {
	Text(
		text = stringResource(id = R.string.app_name) ,
		color = Color.White ,
		fontWeight = FontWeight.Bold ,
		fontSize = 20.sp ,
		fontStyle = FontStyle.Italic ,
		textAlign = TextAlign.End ,
		modifier = Modifier
			.background(MaterialTheme.colors.primary)
			.padding(8.dp)
			.width(200.dp)
	)
}

@Composable
private fun TextUsingStyle() {
	Text(
		text = stringResource(id = R.string.app_name) ,
		style = MaterialTheme.typography.subtitle1 ,
		color = MaterialTheme.typography.subtitle1.color ,
		modifier = Modifier
			.background(MaterialTheme.colors.secondary)
			.padding(8.dp)
	)
}

@Composable
private fun TextUsingAnnotations() {
	Text(
		text = buildAnnotatedString {

			withStyle(
				ParagraphStyle(
					textAlign = TextAlign.Center

				)
			) {
				withStyle(
					SpanStyle(
						color = Color.Blue ,
						fontWeight = FontWeight.ExtraLight ,
						fontSize = 55.sp
					)
				) {
					append("A")
				}

				append("K")
				append("A")
				withStyle(
					SpanStyle(
						color = Color.Red ,
						fontWeight = FontWeight.SemiBold ,
						fontSize = 55.sp
					)
				) {
					append("S")
				}
				append("H")
			}

		} , modifier = Modifier.width(200.dp) ,
		fontSize = 40.sp
	)

}

@Composable
fun RepeatText() {

	Text(
		text = "Hello World!! ".repeat(50) ,
		maxLines = 3 ,
		overflow = TextOverflow.Ellipsis
	)

}