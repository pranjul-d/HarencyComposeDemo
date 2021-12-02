package com.macamps.harencycomposedemo

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.macamps.harencycomposedemo.data.CountriesItem
import com.macamps.harencycomposedemo.ui.theme.Purple500
import com.macamps.harencycomposedemo.utils.Utilities
import com.macamps.harencycomposedemo.ui.auth.viewModel.AuthSharedViewModel
import java.util.*

@Composable
fun CountryCodeScreen(navController: NavController, sharedViewModel: AuthSharedViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val countries = Utilities.generateList(context)
    var filteredCountries: ArrayList<CountriesItem>

    var search by remember { mutableStateOf(TextFieldValue("")) }
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .padding()
                    .padding(16.dp)
                    .clickable {

                        navController.navigateUp()
                    },
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null
            )
            Text(
                text = "Country Codes",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )
        }

        TextField(value = search,
            onValueChange = {
                search = it


            }, modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .border(
                    shape = RoundedCornerShape(16.dp),
                    width = 1.dp,
                    color = Purple500
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                focusedIndicatorColor = Color.White,
            ),
            trailingIcon = {
                Image(
                    painter =
                    painterResource(id = R.drawable.ic_search),
                    contentDescription = null
                )
            })

        LazyColumn(
            modifier = Modifier.padding(5.dp),
        ) {
            val searchText = search.text
            filteredCountries = if (searchText.isEmpty()) {
                countries
            } else {
                val result = ArrayList<CountriesItem>()
                for (country in countries) {
                    if (country.name.lowercase(Locale.getDefault())
                            .contains(searchText.lowercase(Locale.getDefault()))
                    ) {
                        result.add(country)
                    }
                }
                result
            }

            items(filteredCountries) { country ->

                CountryCodeCard(country, onItemClick = {
//                    navController.previousBackStackEntry?.savedStateHandle?.set("data", country)
                    sharedViewModel.mutableCountryCode.value=country
//                    arguments?.apply {
//                        putParcelable("data", country)
//                    }
                    navController.navigateUp()

                })

            }
        }
    }
}

fun findCountry(string: String, countries: ArrayList<CountriesItem>)
        : ArrayList<CountriesItem> {
    val list = arrayListOf<CountriesItem>()
    countries.forEach {
        if (string.lowercase().contains(it.name.lowercase())) {
            list.add(it)
            Log.e("TAG", "findCountry: $list")
        }
    }
    return if (list.isNotEmpty()) list else countries
}