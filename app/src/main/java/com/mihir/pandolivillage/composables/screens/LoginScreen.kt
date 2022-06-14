package com.mihir.pandolivillage.composables.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.startIntentSenderForResult
import androidx.navigation.NavController
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.mihir.pandolivillage.R
import com.mihir.pandolivillage.ui.theme.Background
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.mihir.pandolivillage.HomeActivity
import com.mihir.pandolivillage.MainActivity
import org.json.JSONObject

@Composable
fun LoginScreen(
    navController: NavController,
    oneTapClient: SignInClient
) {
    var username by remember{
        mutableStateOf("")
    }
    var password by remember{
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    var isError by rememberSaveable {
        mutableStateOf(false)
    }

    var isLoading by rememberSaveable {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    val requestQueue  = Volley.newRequestQueue(context)

    val sharedPreferences = context.getSharedPreferences("MihirShared" , Context.MODE_PRIVATE)

    val signInRequest = BeginSignInRequest.builder()
        .setPasswordRequestOptions(
            BeginSignInRequest.PasswordRequestOptions.builder()
                .setSupported(true)
                .build())
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId("203644075812-5lq800tf306a2tqkt3ufkbcnu9339imf.apps.googleusercontent.com")
                .setFilterByAuthorizedAccounts(false)
                .build())
        .setAutoSelectEnabled(false)
        .build()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxWidth(0.6f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                value = username,
                isError = isError,
                onValueChange = { username = it } ,
                label = { Text(text = "Username") }
            )
            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible)
                        R.drawable.visibility
                    else R.drawable.hide

                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(
                        modifier = Modifier.size(25.dp),
                        onClick = {passwordVisible = !passwordVisible}
                    ){
                        Icon(painter = painterResource(id = image), description)
                    }
                },
                singleLine = true,
                value = password,
                onValueChange = { password = it } ,
                label = { Text(text = "Password") }
            )
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black, contentColor = Color.White),
                onClick = {
                    if(username.isNotEmpty() && password.isNotEmpty())
                    {
                        val setUsername = username
                        isLoading = true
                        val request = object : StringRequest(
                            Method.POST,
                            context.getString(R.string.database_address)+"getUser.php",
                            {
                                isLoading = false
                                when(JSONObject(it).getInt("result")){
                                    0 -> { Toast.makeText(context,"Server Not Responding", Toast.LENGTH_LONG).show() }
                                    1 -> {
                                        val editor: SharedPreferences.Editor =  sharedPreferences.edit()
                                        editor.putString("username",setUsername).apply()
                                        context.startActivity(Intent(context, HomeActivity::class.java))
                                    }
                                    2 -> isError = true
                                }
                            },
                            {
                                println(it)
                            }
                        ){
                            override fun getParams(): MutableMap<String, String>? {
                                val params = HashMap<String,String>()
                                params["username"] = username
                                params["password"] = password

                                return params
                            }
                        }

                        requestQueue.add(request)
                    }
                    else
                    {
                        isError = true
                    }

                }
            ) {

                if(isLoading) CircularProgressIndicator(
                    strokeWidth = 5.dp,
                    color = Color.White
                )
                else Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black, contentColor = Color.White),
                onClick = { navController.navigate("Register") }
            ) {
                Text(text = "Register")
            }
            
            Spacer(modifier = Modifier.size(15.dp))
            Text(text = "OR")
            Spacer(modifier = Modifier.size(15.dp))

            IconButton(onClick = {

                oneTapClient.beginSignIn(signInRequest)
                    .addOnSuccessListener { result ->
                        try {
                            startIntentSenderForResult(
                                context as Activity,
                                result.pendingIntent.intentSender,123 ,
                                null, 0, 0, 0, null)
                        } catch (e: IntentSender.SendIntentException) {
                            println("Couldn't start One Tap UI: ${e.localizedMessage}")
                        }
                    }
                    .addOnFailureListener { e ->
                        println(e)
                    }

            }) {
                Image(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "" )
            }

        }
    }

    if(isError)
    {
        AlertDialog(
            onDismissRequest = {
                isError = false
            },
            title = {
                Text(text = "Login Failed")
            },
            text = {
                Text("Enter Correct Email or Password")
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black, contentColor = Color.White),
                        onClick = { isError = false }
                    ) {
                        Text(text = "Dismiss")
                    }
                }
            })

    }

}


