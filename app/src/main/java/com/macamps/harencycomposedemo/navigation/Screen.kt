package com.macamps.harencycomposedemo.navigation

sealed class Screen(val route:String){
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")
    object LoginScreen : Screen("login_screen")
    object SignUpScreen : Screen("signup_screen")
    object ForgotPasswordScreen : Screen("forgot_password_screen")
    object CountryCodeScreen : Screen("countryCode_screen")
    fun withArgs(vararg args:String):String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
}
