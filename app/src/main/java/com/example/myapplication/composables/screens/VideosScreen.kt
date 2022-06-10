package com.example.myapplication.composables.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.composables.components.YoutubeList
import com.example.myapplication.helpers.VideosViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VideosScreen(sharedVideosViewModel: VideosViewModel) {

    var videoList = sharedVideosViewModel.getList()

    Scaffold(
        topBar = {
            Box(modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .background(Color.White),
                contentAlignment = Alignment.Center
            )
            {
                androidx.compose.material3.Text(
                    text = "Live News",
                    color = Color.Black,
                    fontWeight = FontWeight.W700,
                    fontSize = MaterialTheme.typography.h4.fontSize,
                    maxLines = 1
                )
            }

        },
        modifier = Modifier.fillMaxSize()
    ){
        Box(modifier = Modifier.padding(it).fillMaxSize()){
            YoutubeList(list = videoList)
        }

    }
}


