package com.macamps.harencycomposedemo.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.macamps.harencycomposedemo.CountryCodeScreen
import com.macamps.harencycomposedemo.ui.auth.login.HarencyLoginScreen
import com.macamps.harencycomposedemo.ui.auth.login.viewModel.LoginSharedViewModel
import com.macamps.harencycomposedemo.ui.auth.signup.SignUpScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val sharedViewModel = hiltViewModel<LoginSharedViewModel>()

    NavHost(
        navController = navController,
        startDestination = Screen.SignUpScreen.route
    ) {

        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen()
        }
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