package com.example.myapplication.helpers

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.myapplication.composables.model.YoutubeVideo

class VideosViewModel : ViewModel() {

     private var videos by mutableStateOf<List<YoutubeVideo>?>(null)

    fun getList(): List<YoutubeVideo>? {
        return videos
    }

    fun setList(list : List<YoutubeVideo>)  {
        videos = list
    }
}


