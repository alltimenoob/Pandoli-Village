package com.mihir.pandolivillage.helpers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mihir.pandolivillage.composables.model.Card

class PlacesViewModel() : ViewModel() {

    var places by mutableStateOf<Card?>(null)
        private set

    fun addPlaces(card: Card){
        places = card
    }

}