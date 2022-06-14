package com.mihir.pandolivillage

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mihir.pandolivillage.composables.screens.LoginScreen
import com.mihir.pandolivillage.composables.screens.RegisterScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : ComponentActivity() {

    lateinit var oneTapClient : SignInClient
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = this.getSharedPreferences("MihirShared" ,Context.MODE_PRIVATE)

        oneTapClient = Identity.getSignInClient(this)

        val auth = Firebase.auth

        setContent{
                val systemUiController = rememberSystemUiController()

                systemUiController.setSystemBarsColor(
                    color = Color.White
                )

                val navController = rememberNavController()

                NavHost(navController = navController , startDestination = "Login"){
                    composable(route = "Login"){
                        LoginScreen(navController,oneTapClient)
                    }
                    composable(route = "Register"){
                        RegisterScreen(navController,auth)
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
                        this.startActivity(Intent(this,HomeActivity::class.java))
                    }

                } catch (e: ApiException) {
                    println(e)
                }
            }
        }
    }


}