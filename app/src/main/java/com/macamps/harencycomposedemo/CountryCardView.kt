package com.macamps.harencycomposedemo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.macamps.harencycomposedemo.data.CountriesItem

@Composable
fun CountryCodeCard(country: CountriesItem?, onItemClick:()->Unit) {

    Card(
        modifier = Modifier.padding(10.dp).clickable { onItemClick() },
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 22.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            country?.name?.let {
                Text(
                    text = it,
                    style = TextStyle(
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            country?.codeTelePhone?.let {
                Text(
                    text = "+${it}",
                    modifier = Modifier.fillMaxWidth(),

                    style = TextStyle(
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }

}