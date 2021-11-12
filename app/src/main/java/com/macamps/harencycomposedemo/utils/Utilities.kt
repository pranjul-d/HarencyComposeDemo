package com.macamps.harencycomposedemo.utils

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.macamps.harencycomposedemo.data.CountriesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
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

fun <T> toResultFlow(call: suspend () -> Response<T>?): Flow<State<T>?> {
    return flow {
        emit(State.Loading)

        try {
            val c = call()
            c?.let {
                if (c.isSuccessful) {
                    emit(State.Success(c.body()))
                } else {
                    c.errorBody()?.let {
                        val error = it.string()
                        it.close()
                        emit(State.Failure(error))
                    }
                }
            }
        } catch (e: Exception) {
            emit(State.Failure(e.toString()))
        }

    }.flowOn(Dispatchers.IO)
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