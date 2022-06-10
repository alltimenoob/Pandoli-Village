package com.example.myapplication.composables.constants


class Card(private var image: Int, private var title: String, private var description: String) {

    fun getImage() : Int
    {
        return image
    }

    fun getTitle() : String
    {
        return title
    }

    fun getDescription() : String
    {
        return description
    }

}