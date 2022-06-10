package com.example.myapplication.helpers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplication.composables.constants.Card

class PlacesViewModel(

) : ViewModel() {

    var _places by mutableStateOf<Card?>(null)
        private set

    fun addPlaces(card: Card){
        _places = card
    }

}