package com.example.myapplication.composables.screens

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
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Background
import com.google.firebase.auth.FirebaseAuth

@Composable

fun RegisterScreen(navController: NavController,auth: FirebaseAuth) {
    var emailId by remember{
        mutableStateOf("")
    }
    var password by remember{
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    var isError by rememberSaveable {
        mutableStateOf(false)
    }

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
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black
                ),
                singleLine = true,
                value = emailId,
                onValueChange = { emailId = it } ,
                label = { Text(text = "Email") }
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
                onValueChange = { password = it  } ,
                label = { Text(text = "Password") }
            )

            Spacer(modifier = Modifier.height(15.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black, contentColor = Color.White),
                onClick = {
                    if(emailId.isNotEmpty() && password.isNotEmpty() && password.length > 6)
                    {
                        auth.createUserWithEmailAndPassword(emailId, password)
                            .addOnCompleteListener { task->
                                if(task.isSuccessful)
                                {
                                    navController.popBackStack()
                                    auth.signOut()
                                }
                                else
                                {
                                    isError = true
                                }
                            }
                            .addOnFailureListener{
                                println(it)
                            }
                    }
                    else
                    {
                        isError = true
                    }
                }
            ) {
                Text(text = "Register")
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
                Column {
                    Text("Something went try!")
                    Spacer(modifier = Modifier.size(5.dp))
                    Text("Try another email or password.")
                }
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