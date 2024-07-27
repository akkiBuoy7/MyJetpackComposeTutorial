package com.example.jetpackapplication.basics.side_effect

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val TAG = "SideEffect"

@Composable
fun LaunchedEffectComposable(modifier: Modifier = Modifier) {

    val counter = remember {
        mutableStateOf(0)
    }

    /*
    Everytime if key1 value changes then only launched effect will run
    If screen is rotated then exception will occur and coroutine will relaunch
    Launched effect can only be launched inside a composable so it can't be run
    on button click at will.
    It runs only during startup of the composable. It never runs on recomposition
    The coroutine scope that launches it belong to Composable so it cannot be
    controlled
     */
    LaunchedEffect(key1 = Unit) {

        Log.d(TAG , "LaunchedEffectComposable: Started")

        try {
            for (i in 1..10) {
                counter.value++
                delay(1000)
            }

        } catch (e: Exception) {
            Log.d(TAG , "LaunchedEffectComposable: Exception ${e.toString()}")

        }
    }
    var text = "Counter is running ${counter.value}"
    if (counter.value == 10) {
        counter.value = 0
        text = "Counter stopped!!!"
    }

    Column(modifier = modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = text)
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Launched Effect")
        }
    }
}

@Composable
fun CoroutineScopeComposable() {
    val counter = remember {
        mutableStateOf(0)
    }
    val scope = rememberCoroutineScope()

    var text = "Counter is running ${counter.value}"
    if (counter.value == 10) {
        counter.value = 0
        text = "Counter stopped!!!"
    }

    Column(
        Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(text = text)
        Button(onClick = {
            /*
            Can run a scope on button click at will

             */
            scope.launch {
                Log.d(TAG , "CoroutineScopeComosable: Counter Started")
                try {
                    for (i in 1..10) {
                        counter.value++
                        delay(1000)
                    }

                } catch (e: Exception) {
                    Log.d(TAG , "CoroutineScopeComosable: Exception:${e.toString()}")
                }
            }
        }) {
            Text(text = "Start Counter")

        }
    }
}

fun a() {
    Log.d("RememberUpdatedState", "a: This is A")
}

fun b() {
    Log.d("RememberUpdatedState", "b: This is B")
}

@Composable
fun Splash() {
    var state = remember {
        mutableStateOf(::a)
    }
    Button(onClick = { state.value = ::b }) {
        Text(text = "Click to change state")
    }
    Landing(state.value)
}

@Composable
fun Landing(onTimeout: () -> Unit) {

    val updatedTimeoutSate by rememberUpdatedState(newValue = onTimeout)


    LaunchedEffect(key1 = Unit) {
        delay(10000)
        /*
        LaunchedEffect does not run on recomposition, it runs on startup
        so when on button click even though function is B
        it will print A as the onTimeout function
         */


        /*
        rememberUpdatedState hold the updated value if the state always
        if it is changed at all
        So onTimeout has the updated value of function B on button click,
        hence rememberUpdatedState delegates it to updatedTimeoutSate
        So it will print function B
         */
        updatedTimeoutSate()
        //onTimeout() // => It would have print A
    }
}

@Composable
fun ProducedStateComposable() {

    /*
    Using LaunchEffect we can modify a state asynchronously
    but that state object we need to create separately
     */

//    val state1 = remember {
//        mutableStateOf(0)
//    }
//    LaunchedEffect(key1 = Unit) {
//        while (true) {
//            delay(16)
//            state1.value = (state1.value + 20) % 360
//        }
//    }

    /*
    produceState provides both state and coroutine scope itself
    so no need to create state object separately.
     */
    val state = produceState(initialValue = 0) {
        while (true) {
            delay(16)
            value = (value + 20) % 360
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                imageVector = Icons.Default.Refresh, contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    //.rotate(state1.value.toFloat())
                    .rotate(state.value.toFloat())
            )
            Text(text = "Loading")

        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun DerivedState() {
    val tableOf = remember {
        mutableStateOf(1)
    }
    val multiplier = produceState(initialValue = 1) {
        repeat(9){
            delay(1000)
            value+=1
        }
        
    }

    val updatedMultiplier by rememberUpdatedState(newValue = multiplier)

    if (updatedMultiplier.value==10){
        UpdateTable(updatedMultiplier,tableOf)
    }

    val derivedState =
        derivedStateOf {
            "${tableOf.value} * ${multiplier.value} = ${tableOf.value * multiplier.value}"
        }


    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Text(text = derivedState.value)
    }

}

@Composable
fun UpdateTable(updatedMultiplier: State<Int>, tableOf: MutableState<Int>) {
    LaunchedEffect(key1 =true) {
        Log.d(TAG , "DerivedState: ${updatedMultiplier.value}")
        if (updatedMultiplier.value==10){
            tableOf.value +=1
        }
    }
}