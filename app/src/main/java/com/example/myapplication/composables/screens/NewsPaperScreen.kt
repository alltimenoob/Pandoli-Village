package com.example.myapplication.composables.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.myapplication.composables.constants.News
import com.example.myapplication.ui.theme.Background
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@SuppressLint("SetJavaScriptEnabled")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsPaperScreen(link : String?){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ){
        if (link != null) {
            rememberWebViewState(link).apply {
                WebView(state = this,
                    onCreated = {
                        it.settings.javaScriptEnabled = true
                        it.settings.domStorageEnabled = true
                    },
                    captureBackPresses = link == News.Gujaratsamachar.link
                )
            }
        }
    }
}