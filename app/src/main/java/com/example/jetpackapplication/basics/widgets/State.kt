package com.example.jetpackapplication.basics.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/*
rememberSaveable -> will remember even during configuration changes
*/

@Preview
@Composable
fun BuildScreen() {
    // state hoisting at the top level widget
    val count: MutableState<Int> = rememberSaveable {
        mutableStateOf(0)
    }
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        BuildCounter(count.value) { count.value++ }
        BuildMessageBar(count.value)
    }
}

@Composable
fun BuildMessageBar(value: Int) {
    Card(elevation = 3.dp) {

        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)) {
            Image(
                imageVector = Icons.Outlined.AddCircle, contentDescription = "",
                modifier = Modifier.padding(10.dp)
            )
            Text(text = "Updated counter is $value")
        }

    }
}

@Composable
fun BuildCounter(value: Int, increment: () -> Int) {
    // var count = 0

    Column(
    ) {
        Text(text = "You have ${value} notifications")
        Button(onClick = {
            increment()
        }) {
            Text(text = "Increment Counter")
        }
    }
}