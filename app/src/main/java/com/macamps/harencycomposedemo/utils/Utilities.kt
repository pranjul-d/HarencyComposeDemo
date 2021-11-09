package com.macamps.harencycomposedemo.utils

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.macamps.harencycomposedemo.data.CountriesItem
import java.io.IOException
import java.lang.reflect.Type

object Utilities {
    fun generateList(context: Context): ArrayList<CountriesItem> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<CountriesItem?>?>() {}.type
        return gson.fromJson(
            getJsonDataFromAsset(
                context,
                "country_listAll.json"
            ), type
        )
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}

fun Modifier.navigateUp(navController: NavController, data:Any?, route: String) {
    clickable {
        navController.currentBackStackEntry?.savedStateHandle?.set("data", data)
        navController.navigate(route)
    }
}

fun Modifier.navigateUp(navController: NavController, route: String) {
    clickable { navController.navigate(route) }
}

fun <T> SnapshotStateList<T>.swapList(newList: List<T>) {
    clear()
    addAll(newList)
}