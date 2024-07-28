package com.example.jetpackapplication.basics.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackapplication.R

@Preview
@Composable
fun PreviewItemWithColumn() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        getList().map { item ->
            MyListItemCompose(role = item.role, desc = item.desc, img = item.img)
        }
    }
}

@Preview
@Composable
fun PreviewItemWithLazyColumn() {
    LazyColumn(content = {
        items(getList()) { item ->
            MyListItemCompose(role = item.role, desc = item.desc, img = item.img)
        }
    })
}


@Composable
fun MyListItemCompose(role: String, desc: String, img: Int) {

    Card(
        //elevation = 7.dp,
        modifier = Modifier.padding(7.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = img), contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .padding(7.dp)
                    .weight(0.2f)
            )
            ItemDesc(
                role, desc,
                Modifier
                    .padding(7.dp)
                    .weight(0.8f)
            )
        }

    }

}

@Composable
private fun ItemDesc(role: String, desc: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = role ,
            style = MaterialTheme.typography.headlineMedium ,
        )
        Text(
            text = desc, style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Thin,
        )
    }
}

data class MyListItem(val role: String, val desc: String, val img: Int)

fun getList(): MutableList<MyListItem> {
    val list = mutableListOf<MyListItem>()
    list.add(MyListItem(role = "Software Developer" , desc = "Develops software" , img = R.drawable.user))
    list.add(MyListItem(role = "AI & ML" , desc = "Builds models" , img = R.drawable.user1))
    list.add(MyListItem(role = "Data Analyst" , desc = "Analyze data" , img = R.drawable.user2))
    list.add(MyListItem(role = "Manager" , desc = "Manages people" , img = R.drawable.user3))
    list.add(MyListItem(role = "Software Developer" , desc = "Develops software" , img = R.drawable.user))
    list.add(MyListItem(role = "AI & ML" , desc = "Builds models" , img = R.drawable.user1))
    list.add(MyListItem(role = "Data Analyst" , desc = "Analyze data" , img = R.drawable.user2))
    list.add(MyListItem(role = "Manager" , desc = "Manages people" , img = R.drawable.user3))
    list.add(MyListItem(role = "Software Developer" , desc = "Develops software" , img = R.drawable.user))
    list.add(MyListItem(role = "AI & ML" , desc = "Builds models" , img = R.drawable.user1))
    list.add(MyListItem(role = "Data Analyst" , desc = "Analyze data" , img = R.drawable.user2))
    list.add(MyListItem(role = "Manager" , desc = "Manages people" , img = R.drawable.user3))
    list.add(MyListItem(role = "Software Developer" , desc = "Develops software" , img = R.drawable.user))
    list.add(MyListItem(role = "AI & ML" , desc = "Builds models" , img = R.drawable.user1))
    list.add(MyListItem(role = "Data Analyst" , desc = "Analyze data" , img = R.drawable.user2))
    list.add(MyListItem(role = "Manager" , desc = "Manages people" , img = R.drawable.user3))

    return list

}