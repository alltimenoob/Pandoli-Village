package com.mihir.pandolivillage.composables.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mihir.pandolivillage.composables.model.News
import com.mihir.pandolivillage.ui.theme.Background
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