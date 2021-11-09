package com.macamps.harencycomposedemo

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.macamps.harencycomposedemo.navigation.Screen
import com.macamps.harencycomposedemo.ui.login.HarencyLoginScreen
import com.macamps.harencycomposedemo.viewModel.LoginSharedViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val sharedViewModel = hiltViewModel<LoginSharedViewModel>()

    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {

        composable(route = Screen.LoginScreen.route) {
            HarencyLoginScreen(navController = navController, sharedViewModel)

//            entry.savedStateHandle.get<UserDetail>("user").let { HarencyLoginScreen(it) }
//            navEntry.arguments?.getParcelable<CountriesItem>("data")

        }
        composable(route = Screen.CountryCodeScreen.route) {

            CountryCodeScreen(navController, sharedViewModel)
        }
    }

}