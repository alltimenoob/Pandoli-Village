package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.composables.screens.LoginScreen
import com.example.myapplication.composables.screens.RegisterScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : ComponentActivity() {

    lateinit var oneTapClient : SignInClient
    lateinit var signInRequest : BeginSignInRequest
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = this.getSharedPreferences("MihirShared" ,Context.MODE_PRIVATE)

        if(sharedPreferences.getString("email","default") != "default" ){
            this.startActivity(Intent(this,MainActivity::class.java))
        }
        else
        {
            oneTapClient = Identity.getSignInClient(this)

            signInRequest = BeginSignInRequest.builder()
                .setPasswordRequestOptions(
                    BeginSignInRequest.PasswordRequestOptions.builder()
                        .setSupported(true)
                        .build())
                .setGoogleIdTokenRequestOptions(
                    BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId("203644075812-5lq800tf306a2tqkt3ufkbcnu9339imf.apps.googleusercontent.com")
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                .setAutoSelectEnabled(true)
                .build()

            val auth = Firebase.auth

            setContent{
                MyApplicationTheme{
                    val navController = rememberNavController()

                    NavHost(navController = navController , startDestination = "Login"){
                        composable(route = "Login"){
                            LoginScreen(navController,auth,oneTapClient,signInRequest,sharedPreferences)
                        }
                        composable(route = "Register"){
                            RegisterScreen(navController,auth)
                        }
                    }

                }
            }
        }
    }

    @SuppressLint("CommitPrefEdits")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            123 -> {
                try {
                    val credential = oneTapClient.getSignInCredentialFromIntent(data)

                    val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                    editor.putString("email",credential.id)
                    editor.putString("name",credential.displayName)
                    editor.apply()

                    if(credential.id != "")
                    {
                        this.startActivity(Intent(this,MainActivity::class.java))
                    }

                } catch (e: ApiException) {
                    println(e)
                }
            }
        }
    }


}