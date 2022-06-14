package com.mihir.pandolivillage.composables.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.mihir.pandolivillage.R
import com.mihir.pandolivillage.ui.theme.Background
import kotlinx.coroutines.coroutineScope
import org.json.JSONObject

@Composable
fun ProfileScreen(
    navController : NavController
) {

    var password by remember{
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    var new_password by remember{
        mutableStateOf("")
    }

    var new_passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    val requestQueue  = Volley.newRequestQueue(context)

    val sharedPreferences = context.getSharedPreferences("MihirShared" , Context.MODE_PRIVATE)

    var isError by rememberSaveable {
        mutableStateOf(false)
    }

    var isLoading by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    )
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            androidx.compose.material3.IconButton(modifier = Modifier
                .height(50.dp)
                .padding(20.dp, 20.dp, 20.dp, 0.dp)
                .background(Background),
                onClick = { navController.popBackStack() }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Hamburger Menu"
                )
            }
            androidx.compose.material3.Text(
                modifier = Modifier
                    .height(50.dp)
                    .padding(20.dp, 20.dp, 20.dp, 0.dp)
                    .background(Background),
                text = "Change Password",
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontWeight = FontWeight.W600,
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Background),
            contentAlignment = Alignment.TopCenter
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

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
                    visualTransformation = if (new_passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image = if (new_passwordVisible)
                            R.drawable.visibility
                        else R.drawable.hide

                        val description = if (new_passwordVisible) "Hide password" else "Show password"

                        IconButton(
                            modifier = Modifier.size(25.dp),
                            onClick = {new_passwordVisible = !new_passwordVisible}
                        ){
                            Icon(painter = painterResource(id = image), description)
                        }
                    },
                    singleLine = true,
                    value = new_password,
                    onValueChange = { new_password = it } ,
                    label = { Text(text = "New Password") }
                )

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black, contentColor = Color.White),
                    onClick = {
                        if(
                            password.isNotEmpty() &&
                            new_password.isNotEmpty() &&
                            password.length > 6 &&
                            new_password.length > 6 &&
                            new_password != password
                        ) {
                            isLoading = true
                            val request = object : StringRequest(
                                Method.POST, context.getString(R.string.database_address)+"setPassword.php", {

                                    isLoading = false
                                    when(JSONObject(it).getInt("result")){
                                        0 -> {
                                            Toast.makeText(context,"Server not responding.",Toast.LENGTH_LONG).show()
                                        }
                                        1 -> {
                                            Toast.makeText(context,"Password Changed.",Toast.LENGTH_LONG).show()
                                            navController.popBackStack()
                                        }
                                        2 -> isError = true
                                    }
                                     }, {
                                    println(it)
                                }
                            ){
                                override fun getParams(): MutableMap<String, String>? {
                                    val params = HashMap<String,String>()

                                    params["username"] = sharedPreferences.getString("username","default").toString()
                                    params["password"] = password
                                    params["new_password"] = new_password


                                    println(params["username"])

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