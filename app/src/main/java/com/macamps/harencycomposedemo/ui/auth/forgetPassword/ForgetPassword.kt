package com.macamps.harencycomposedemo.ui.auth.forgetPassword

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.macamps.harencycomposedemo.R
import com.macamps.harencycomposedemo.data.SuccessModel
import com.macamps.harencycomposedemo.navigation.Screen
import com.macamps.harencycomposedemo.ui.auth.login.showDialog
import com.macamps.harencycomposedemo.ui.auth.viewModel.AuthSharedViewModel
import com.macamps.harencycomposedemo.ui.theme.Purple500
import com.macamps.harencycomposedemo.ui.theme.fonts
import com.macamps.harencycomposedemo.utils.ApiState
import com.macamps.harencycomposedemo.utils.Constants
import com.macamps.harencycomposedemo.utils.DrawableWrapper
import com.macamps.harencycomposedemo.utils.toast
import kotlinx.coroutines.launch
import retrofit2.Response


@Composable
fun ForgetPassword(
    navController: NavHostController,
    sharedViewModel: AuthSharedViewModel = hiltViewModel()
) {
    var phoneNumber by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val maxLength = 10
    val country = sharedViewModel.countryLiveData.observeAsState()
    val value = if (country.value != null) "+${country.value?.codeTelePhone}" else "+91"

    val context = (LocalContext.current as? Activity)
    val scope = rememberCoroutineScope()
    val sendOtpState = sharedViewModel.mSendStateFlow.collectAsState()
    val isLoading = sharedViewModel.isLoading.collectAsState()

    when (sendOtpState.value) {
        is ApiState.Success -> {
            val response = (sendOtpState.value as ApiState.Success<Response<SuccessModel>>)
            response.data.body()?.message?.let { context?.toast(it) }
        }
        is ApiState.Loading -> {
            showDialog(sharedViewModel)
        }
        is ApiState.Error -> {}
        ApiState.Empty -> {}
        else -> Unit
    }
    Box {
        Column(modifier = Modifier.fillMaxSize().background(Color.White).padding(16.dp)) {
            Image(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = null, modifier = Modifier.clickable {
                    navController.navigateUp()
                }
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(R.string.forgot_password),
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fonts
                )
            )
            Text(
                text = stringResource(R.string.please_enter_your_number),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = fonts
                )
            )
            Card(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)
                    .padding(vertical = 10.dp)
            ) {
                Column(modifier = Modifier.padding(vertical = 20.dp)) {

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
                                text = value,
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Black
                                ),
                                modifier = Modifier
                                    .padding(
                                        top = 14.dp,
                                        bottom = 14.dp,
                                        start = 20.dp,
                                    ).clickable {
                                        navController.navigate(Screen.CountryCodeScreen.route)
                                    },
                            )
                        }
                    }
                    Button(
                        onClick = {
                            if (phoneNumber.text == "") {
                                context?.toast("Please Enter your phone number!")
                                return@Button
                            } else {
                                scope.launch {
                                    sharedViewModel.sendOtp(
                                        value,
                                        phoneNumber.text,
                                        Constants.FORGET_PASSWORD
                                    )
                                }
                            }

                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp, vertical = 10.dp)
                            .height(42.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(R.color.mainBgColor)
                        ),
                        shape = RoundedCornerShape(70)
                    ) {
                        Text(
                            stringResource(R.string.get_an_otp),
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
            }
        }
    }
}