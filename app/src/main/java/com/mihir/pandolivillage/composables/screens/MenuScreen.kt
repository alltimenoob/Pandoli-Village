package com.mihir.pandolivillage.composables.screens

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mihir.pandolivillage.LoginActivity
import com.mihir.pandolivillage.ui.theme.Background
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mihir.pandolivillage.R

@Composable
fun MenuScreen(
    navController: NavController,
    sharedPreferences: SharedPreferences
){
    val context = LocalContext.current

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
            IconButton(modifier = Modifier
                .height(50.dp)
                .padding(20.dp, 20.dp, 20.dp, 0.dp)
                .background(Background) ,
                onClick = { navController.popBackStack()}) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Hamburger Menu"
                )
            }
            androidx.compose.material.Text(
                modifier = Modifier
                    .height(50.dp)
                    .padding(20.dp, 20.dp, 20.dp, 0.dp)
                    .background(Background),
                text = "Menu",
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontWeight = FontWeight.W600,
            )
        }
        
        Spacer(modifier = Modifier.height(30.dp))
        
        Item(text = "Change Password") {
            navController.navigate("Profile")
        }

        Item(text = "Useful Links") {
            navController.navigate("UsefulLinks")
        }

        Item(text = "Share") {
            context.startActivity(
                Intent.createChooser(
                    Intent()
                        .setAction(Intent.ACTION_SEND)
                        .setType("text/plain")
                        .putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/"),
                    "Share Pandoli Village"
                )
            )
        }

        Item(text = "Sign Out") {
            sharedPreferences
                .edit()
                .clear()
                .apply()
            context.startActivity(Intent(context, LoginActivity::class.java))
        }



    }

}

@Composable
fun Item(text : String,onClick : ()->Unit){
    Text(text = text ,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 10.dp, 20.dp, 0.dp)
            .height(50.dp)
            .clickable {
                onClick()
            },
        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
        fontWeight = FontWeight.W700
    )
}