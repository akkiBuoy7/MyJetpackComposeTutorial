package com.example.jetpackapplication.basics.widgets.constraint_layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainConstraintFile() {
	Column {
		NormalCountryCard()
		Spacer(modifier = Modifier.height(20.dp))
		CountryCardWithGuidelineConstraintLayout()
	}
}