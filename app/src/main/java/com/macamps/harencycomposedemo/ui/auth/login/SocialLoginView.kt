package com.macamps.harencycomposedemo.ui.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.macamps.harencycomposedemo.R

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