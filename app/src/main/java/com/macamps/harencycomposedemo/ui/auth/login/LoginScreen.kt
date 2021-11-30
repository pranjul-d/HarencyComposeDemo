package com.macamps.harencycomposedemo.ui.auth.login

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.macamps.harencycomposedemo.DividerView
import com.macamps.harencycomposedemo.R
import com.macamps.harencycomposedemo.data.UserRegisterModel
import com.macamps.harencycomposedemo.navigation.Screen
import com.macamps.harencycomposedemo.ui.theme.Purple500
import com.macamps.harencycomposedemo.ui.theme.fonts
import com.macamps.harencycomposedemo.utils.ApiState
import com.macamps.harencycomposedemo.utils.DrawableWrapper
import com.macamps.harencycomposedemo.utils.noRippleClickable
import com.macamps.harencycomposedemo.utils.toast
import com.macamps.harencycomposedemo.ui.auth.login.viewModel.LoginSharedViewModel
import kotlinx.coroutines.launch
import retrofit2.Response

@Composable
fun HarencyLoginScreen(
    navController: NavController,
    sharedViewModel: LoginSharedViewModel = hiltViewModel()
) {

    val scrollState = rememberScrollState()

    SnackBarView(sharedViewModel)
    Box(modifier = Modifier.fillMaxSize()) {
        showDialog(sharedViewModel)

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
                    stringResource(R.string.sign_in),
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(stringResource(R.string.sign_in_to_continue))

            }

            Box(modifier = Modifier.padding(vertical = 10.dp)) {
                LoginCardView(navController, sharedViewModel)

            }


        }
//        SnackbarHost(
//            modifier = Modifier.align(Alignment.BottomCenter),
//            hostState = snackbarHostState,
//            snackbar = { snackbarData: SnackbarData ->
//                Card(
//                    shape = RoundedCornerShape(8.dp),
//                    border = BorderStroke(2.dp, Color.White),
//                    modifier = Modifier
//                        .padding(16.dp)
//                        .wrapContentSize()
//                ) {
//                    Column(
//                        modifier = Modifier.padding(8.dp),
//                        verticalArrangement = Arrangement.spacedBy(4.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Text(text = snackbarData.message)
//                    }
//                }
//            }
//        )
    }

}

@Composable
fun SnackBarView(sharedViewModel: LoginSharedViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val response = sharedViewModel.loginApiStateFlow.collectAsState().value

    when (response) {
        is ApiState.Loading -> {

        }
        is ApiState.Success -> {
//            scope.launch {
//                snackbarHostState.showSnackbar(response.data.body().message)
//            }
        }

        is ApiState.Empty -> {}
        else -> {}
    }


}


@Composable
fun LoginCardView(
    navController: NavController,
    sharedViewModel: LoginSharedViewModel,
) {
//    val context = LocalContext.current

    val country = sharedViewModel.countryLiveData.observeAsState()
    val context = (LocalContext.current as? Activity)

//    val country = remember { navController.previousBackStackEntry?.savedStateHandle?.get<CountriesItem>("data") }
//    Log.e("TAG", "LoginCardView: ${getData?.value?.icCode}", )


    var phoneNumber by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val maxLength = 10
/*    val loginResponse = produceState<State<Response<UserRegisterModel>>?>(initialValue = State.Loading){
        value= sharedViewModel.loginLiveData
    }.value*/
    val value = if (country.value != null) "+${country.value?.codeTelePhone}" else "+91"
//    var countryCode by remember {
//        mutableStateOf(value)
//    }
    var password by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var visible by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    val loginResponse = sharedViewModel.loginApiStateFlow.collectAsState()


    when (loginResponse.value) {
        is ApiState.Success -> {
            Toast.makeText(
                context,
                (sharedViewModel.loginApiStateFlow.value as ApiState.Success<Response<UserRegisterModel>>).data.body()?.message,
                Toast.LENGTH_SHORT
            ).show()

        }
        is ApiState.Loading -> {
            Toast.makeText(
                context,
                "Loading",
                Toast.LENGTH_SHORT
            ).show()
        }
        is ApiState.Empty -> {}
        is ApiState.Error -> {}
        else -> Unit
    }

    Column {
        Box(modifier = Modifier.fillMaxSize()) {
            Card(
                elevation = 5.dp,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.padding(10.dp)
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
                                backgroundColor = White,
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
                                    )
                                    .clickable {
                                        navController.navigate(Screen.CountryCodeScreen.route)
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
                        visualTransformation = if (sharedViewModel.isVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = White
                        ),
                        trailingIcon = {
                            val image = if (sharedViewModel.isVisible.value)
                                painterResource(R.drawable.ic_visibility_off_24)
                            else painterResource(R.drawable.ic_visibility_24)


                            IconButton(onClick = {
                                sharedViewModel.isVisible.value = !sharedViewModel.isVisible.value
                            }) {
                                Image(painter = image, "")
                            }
                        }
                    )
                    Text(
                        "Forgot password?",
                        style = TextStyle(
                            color = Purple500,
                            textAlign = TextAlign.End
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                end = 15.dp,
                                bottom = 10.dp
                            )
                    )

                    Button(
                        onClick = {
                            if (phoneNumber.text == "") {
                                context?.toast("Please Enter your phone number!")
                                return@Button
                            } else if (password.text.length < 5) {
                                context?.toast("Please length must be greater than 5")
                                return@Button
                            } else if (password.text == "") {
                                context?.toast("Please Enter your password!")
                                return@Button
                            } else {

                                val hashMap = HashMap<String, String?>()
                                hashMap["phone_number"] = phoneNumber.text
                                hashMap["country_code"] = value
                                hashMap["password"] = password.text
                                hashMap["device_type"] = "1"
                                hashMap["device_token"] = "sss"

                                scope.launch { sharedViewModel.login(hashMap) }
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
                            stringResource(R.string.sign_in),
                            color = White,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }

//            Divider
                    DividerView("Or Sign In")
                    SocialLoginButtons()
                    SignUpBottomView()

                }
            }
        }

        BottomView()
    }


}

@Composable
fun BottomView() {
    Box(
        modifier = Modifier.padding(top = 15.dp).fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_wave),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Image(
            painter = painterResource(id = R.drawable.ic_wave2),

            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SignUpBottomView() {

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
    ) {
        Text(
            "Don't have an Account? ",
            style = TextStyle(
                background = White,
                color = Color.Black,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            ),
            fontFamily = fonts,
            modifier = Modifier
                .padding()
                .background(White)
        )
        Text(
            "Sign Up",
            style = TextStyle(
                background = White,
                color = Purple500,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            ),
            fontFamily = fonts,
            modifier = Modifier
                .padding()
                .background(White).noRippleClickable { }
        )
    }
}


