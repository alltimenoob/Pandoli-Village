package com.example.myapplication.composables.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.Background
import com.example.myapplication.R

@Composable
fun MenuScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    )
    {

        Image(
            modifier = Modifier
                .height(60.dp)
                .padding(20.dp, 20.dp, 20.dp, 0.dp)
                .background(Background)
                .clickable {
                    navController.popBackStack()
                },
            painter = painterResource(id = R.drawable.ic_back),
            contentScale = ContentScale.Crop,
            contentDescription = "Hamburger Menu"
        )
        
        Text(text = "Profile" ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 10.dp, 20.dp, 0.dp)
                .height(50.dp),
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )

        Text(text = "Useful Links" ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 10.dp, 20.dp, 0.dp)
                .height(50.dp),
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )

        Text(text = "Share " ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 10.dp, 20.dp, 0.dp)
                .height(50.dp),
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )

        Text(text = "Log Out" ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 10.dp, 20.dp, 0.dp)
                .height(50.dp),
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )




    }
}