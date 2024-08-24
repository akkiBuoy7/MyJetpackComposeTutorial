package com.example.jetpackapplication.basics.widgets

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.TextUnit

@Composable
fun HyperlinkText(
	modifier : Modifier = Modifier ,
	text : String ,
	linkText : List<String> ,
	hyperlinks : List<String> ,
	linkTextColor : Color = MaterialTheme.colorScheme.primary ,
	linkTextFontWeight : FontWeight = FontWeight.Normal ,
	linkTextDecoration : TextDecoration = TextDecoration.Underline ,
	fontSize : TextUnit = TextUnit.Unspecified ,
	fontFamily : FontFamily = FontFamily.Monospace
) {
	val uriHandler = LocalUriHandler.current

	val annotatedString = buildAnnotatedString {
		var lastIndex = 0
		linkText.forEachIndexed { index , link ->
			// lastIndex is the pos from here search of link shd start in text
			val startIndex = text.indexOf(link , lastIndex)
			// end index of the link in text
			val endIndex = startIndex + link.length
			/*
			Searching for non link text between 0 and link start index in 1st ite
			then after last index is increased by 1, search again between
			non link text(last index) and the start index of the next link in 2nd ite
			this line of code searches for non link text bw processed string
			and start of link in that iteration
			 */
			if (startIndex > lastIndex) append(text.substring(lastIndex , startIndex))
			// attach the hyperlink to get url with a specific style
			val linkUrL = LinkAnnotation.Url(
				hyperlinks[index] , TextLinkStyles(
					SpanStyle(
						color = linkTextColor ,
						fontSize = fontSize ,
						fontWeight = linkTextFontWeight ,
						textDecoration = linkTextDecoration ,
						fontFamily = fontFamily
					)
				)
			) {
				// make the url clickable
				val url = (it as LinkAnnotation.Url).url
				uriHandler.openUri(url)
			}
			// append the url to the annotated string
			withLink(linkUrL) { append(link) }
			append(" ")
			/*
			increase by 1 as next search for link shd start from this lastIndex of the
			created annotated string.
			 */

			lastIndex = endIndex + 1
		}
		// add the remaining string after all hyperlink looping is done
		// append from lastIndex upto the text.length to get the full text
		if (lastIndex < text.length) {
			append(text.substring(lastIndex))
		}
		// apply style for the whole text
		addStyle(
			style = SpanStyle(
				fontSize = fontSize , fontFamily = fontFamily
			) , start = 0 , end = text.length
		)
	}
	Text(text = annotatedString , modifier = modifier)
}