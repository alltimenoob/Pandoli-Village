package com.mihir.pandolivillage.composables.screens

import android.widget.Toast
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
import androidx.navigation.NavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.mihir.pandolivillage.R
import com.mihir.pandolivillage.ui.theme.Background
import com.google.firebase.auth.FirebaseAuth
import org.json.JSONObject

@Composable

fun RegisterScreen(navController: NavController,auth: FirebaseAuth) {
    var email_id by remember{
        mutableStateOf("")
    }
    var password by remember{
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    var mobile_no by remember{
        mutableStateOf("")
    }

    var username by remember{
        mutableStateOf("")
    }

    var name by remember{
        mutableStateOf("")
    }

    val context = LocalContext.current

    val requestQueue  = Volley.newRequestQueue(context)

    var isError by rememberSaveable {
        mutableStateOf(false)
    }

    var isLoading by rememberSaveable {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxWidth(0.9f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black
                ),
                singleLine = true,
                value = name,
                onValueChange = { name = it } ,
                label = { Text(text = "Name") }
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                readOnly = true,
                enabled = true,
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black
                ),
                singleLine = true,
                value = username,
                onValueChange = { username = it } ,
                label = { Text(text = "Username") }
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black
                ),
                singleLine = true,
                value = email_id,
                onValueChange = { email_id = it } ,
                label = { Text(text = "Email") }
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
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
                onValueChange = { password = it  } ,
                label = { Text(text = "Password") }
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                value = mobile_no,
                onValueChange = { mobile_no = it } ,
                label = { Text(text = "Mobile") }
            )

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black, contentColor = Color.White),
                onClick = {
                    if(
                        email_id.isNotEmpty() &&
                        password.isNotEmpty() &&
                        name.isNotEmpty() &&
                        username.isNotEmpty() &&
                        mobile_no.isNotEmpty() &&
                        password.length > 6 &&
                        mobile_no.length == 10
                    ) {
                        isLoading = true
                        val request = object : StringRequest(
                            Method.POST, context.getString(R.string.database_address)+"setUser.php", {
                                isLoading = false
                                var string = ""
                                when(JSONObject(it).getInt("result")){
                                    0 -> string = "Server not responding"
                                    1 -> string = "Registered Successfully"
                                    2 -> isError = true
                                    3 -> isError = true
                                }
                                Toast.makeText(context,string,Toast.LENGTH_LONG).show()
                                navController.popBackStack() }, {
                                println(it)
                            }
                        ){
                            override fun getParams(): MutableMap<String, String>? {
                                val params = HashMap<String,String>()
                                params["name"] = name
                                params["username"] = username
                                params["password"] = password
                                params["email_id"] = email_id
                                params["mobile_no"] = mobile_no

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
                else Text(text = "Update")
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
                Text(text = "Registration Failed")
            },
            text = {

                Text("Something went try! Check Provided Details")

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