package com.macamps.harencycomposedemo.ui.auth.signup

import android.app.Activity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.macamps.harencycomposedemo.R
import com.macamps.harencycomposedemo.ui.auth.login.viewModel.LoginSharedViewModel
import com.macamps.harencycomposedemo.ui.theme.Purple
import com.macamps.harencycomposedemo.ui.theme.Purple500
import com.macamps.harencycomposedemo.ui.theme.fonts
import com.macamps.harencycomposedemo.utils.DrawableWrapper
import com.macamps.harencycomposedemo.utils.noRippleClickable
import com.macamps.harencycomposedemo.utils.toast

@Preview(showBackground = true)
@Composable
fun SignUpScreen() {
    val scrollState = rememberScrollState()
    Scaffold(modifier = Modifier.fillMaxSize(),
        content = {
            Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

                Column(
                    modifier = Modifier
                        .fillMaxSize(1f)
                        .verticalScroll(state = scrollState)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .padding(top = 15.dp)
                    ) {
                        Text(
                            stringResource(R.string.sign_up),
                            style = TextStyle(
                                fontSize = 28.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        Text(stringResource(R.string.sign_up_to_continue))
                    }

                    Box(modifier = Modifier.padding(vertical = 10.dp)) { SignUpView() }

                }

            }

        },
        bottomBar = {
            Box(
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_wave),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_wave2),
                    modifier = Modifier
                        .padding(top = 10.dp),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null
                )
            }
        })

}

@Composable
fun SignUpView() {
//    val country = sharedViewModel.countryLiveData.observeAsState()
    val context = (LocalContext.current as? Activity)
//    val viewModel = hiltViewModel<LoginSharedViewModel>()
//    val country = remember { navController.previousBackStackEntry?.savedStateHandle?.get<CountriesItem>("data") }
//    Log.e("TAG", "LoginCardView: ${getData?.value?.icCode}", )

    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }


    val maxLength = 10
/*    val loginResponse = produceState<State<Response<UserRegisterModel>>?>(initialValue = State.Loading){
        value= sharedViewModel.loginLiveData
    }.value*/
//    val value = if (country.value != null) "+${country.value?.codeTelePhone}" else "+91"

    var password by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Box {
        Card(
            elevation = 5.dp,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(0.dp)
                .padding(10.dp)
        ) {
            Column(modifier = Modifier.padding(vertical = 26.dp)) {

                Box {
                    TextField(
                        value = phoneNumber,
                        onValueChange = {
                            if (it.text.length <= maxLength) phoneNumber = it

                        },
                        leadingIcon = { Purple500 },
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth(),
                        placeholder = { Text("Phone Number") },

                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        keyboardActions = KeyboardActions(),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                        ),
                    )
                    DrawableWrapper(drawableEnd = R.drawable.ic_arrow_down) {
                        Text(
                            text = "+91",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black
                            ),
                            modifier = Modifier
                                .padding(
                                    top = 14.dp,
                                    bottom = 14.dp,
                                    start = 20.dp,
                                )
                                .clickable {
//                                    navController.navigate(Screen.CountryCodeScreen.route)
                                },
                        )
                    }


                }
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .padding(
                            bottom = 10.dp,
                            start = 20.dp,
                            end = 20.dp
                        ),
                    placeholder = { Text("Password") },
//                    visualTransformation = if (sharedViewModel.isVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    ),
                    trailingIcon = {
//                        val image = if (sharedViewModel.isVisible.value)
//                            painterResource(R.drawable.ic_visibility_24)
//                        else painterResource(R.drawable.ic_visibility_off_24)
//
//
//                        IconButton(onClick = {
//                            sharedViewModel.isVisible.value = !sharedViewModel.isVisible.value
//                        }) {
//                            Image(painter = image, "")
//                        }
                    }
                )
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .padding(
                            bottom = 10.dp,
                            start = 20.dp,
                            end = 20.dp
                        ),
                    placeholder = { Text("Confirm Password") },
//                    visualTransformation = if (sharedViewModel.isVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    )
                )
                Button(
                    onClick = {
                        when {
                            phoneNumber.text == "" -> {
                                context?.toast("Please Enter your phone number!")
                                return@Button
                            }
                            password.text.length < 5 -> {
                                context?.toast("Please length must be greater than 5")
                                return@Button
                            }
                            password.text == "" -> {
                                context?.toast("Please Enter your password!")
                                return@Button
                            }
                            else -> {

                            }
                        }

                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 10.dp)
                        .height(42.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(R.color.mainBgColor)
                    ),
                    shape = RoundedCornerShape(70)
                ) {
                    Text(
                        stringResource(R.string.sign_up),
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
                ) {
                    Text(
                        "Already have an Account! ",
                        style = TextStyle(
                            background = Color.White,
                            color = Color.Black,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        ),
                        fontFamily = fonts,
                        modifier = Modifier
                            .padding()
                            .background(Color.White)
                    )
                    Text(
                        "sign in",
                        style = TextStyle(
                            background = Color.White,
                            color = Purple,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        ),
                        fontFamily = fonts,
                        modifier = Modifier
                            .padding()
                            .background(Color.White).noRippleClickable {

                            }
                    )
                }

            }
        }
    }

}