package com.mihir.pandolivillage.composables.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mihir.pandolivillage.composables.components.ListCard
import com.mihir.pandolivillage.composables.model.News
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(navController: NavController) {

    var list = listOf(News.Sandesh, News.Divyabhaskar, News.Gujaratsamachar, News.Navgujrat)

    Scaffold(
        topBar = {
           Box(modifier = Modifier
               .height(60.dp)
               .fillMaxWidth()
               .background(Color.White),
           contentAlignment = Alignment.Center
           )
           {
               Text(
                   text = "News Papers",
                   color = Color.Black,
                   fontWeight = FontWeight.W700,
                   fontSize = MaterialTheme.typography.h4.fontSize,
                   maxLines = 1
               )
           }

        }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it))
        {
            ListCard(list = list){ item->
                var link = URLEncoder.encode(item.link, StandardCharsets.UTF_8.toString())
                println(link)
                navController.navigate("NewsPaper/$link")
            }
        }
    }
    


}


@SuppressLint("SetJavaScriptEnabled", "JavascriptInterface")
@Composable
fun GetSandesh() {



}
