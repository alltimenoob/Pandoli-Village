package com.example.myapplication.helpers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplication.composables.model.Card

class PlacesViewModel() : ViewModel() {

    var places by mutableStateOf<Card?>(null)
        private set

    fun addPlaces(card: Card){
        places = card
    }

}