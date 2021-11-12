package com.macamps.harencycomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.macamps.harencycomposedemo.ui.theme.HarencyComposeDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoxConstraintsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HarencyComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    Navigation()
                }
            }
        }
    }
}

@Composable
fun SocialLoginButtons() {
    val socialIconList = listOf(
        R.drawable.ic_google,
        R.drawable.ic_instagram,
        R.drawable.ic_facebook,
        R.drawable.ic_twitter_circled,
    )
    LazyRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(socialIconList) { icon ->

            Image(painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.clickable { })

        }
    }
}

@Composable
fun DividerView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Divider(
            color = Color.Gray, modifier = Modifier
                .padding(horizontal = 20.dp)
        )
        Text(
            "Or Sign In",
            style = TextStyle(
                background = Color.White,
                color = Color.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding()
                .fillMaxWidth(.35f)
                .padding(12.dp)
                .background(Color.White)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HarencyComposeDemoTheme {
        Navigation()
    }
}

@Composable
private fun BoxConstraintsView() {
    BoxWithConstraints(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        val boxWithConstraintsScope = this
        val checkChangeState = remember { mutableStateOf(false) }
        Column {
            if (boxWithConstraintsScope.maxHeight >= 200.dp) {
                Text(
                    "This is only visible when the maxHeight is >= 200.dp & height is ${boxWithConstraintsScope.maxHeight}",
                    style = TextStyle(fontSize = 20.sp)
                )

                Checkbox(checked = checkChangeState.value,
                    onCheckedChange = { checkChangeState.value = it })
            } else {
                Text("minHeight: ${boxWithConstraintsScope.minHeight}, maxHeight: ${boxWithConstraintsScope.maxHeight},  minWidth: ${boxWithConstraintsScope.minWidth} maxWidth: ${boxWithConstraintsScope.maxWidth}")

            }
        }
    }
}