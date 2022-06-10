package com.example.myapplication.composables.model

open class YoutubeVideo(private val id : String, private val title : String) {

    fun getId() : String {
        return id
    }

    fun getTitle() : String {
        return title
    }

}