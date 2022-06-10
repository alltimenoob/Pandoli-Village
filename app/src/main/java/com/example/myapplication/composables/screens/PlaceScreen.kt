package com.example.myapplication.composables.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.composables.constants.Card
import com.example.myapplication.helpers.PlacesViewModel

@Composable
fun PlaceScreen(sharedViewModel: PlacesViewModel){

    val place = sharedViewModel._places

    if(place!=null) {

       Column{
           Image(
               modifier = Modifier
                   .fillMaxWidth()
                   .height(IntrinsicSize.Max)
                   .weight(0.6f),
               painter = painterResource(id = place.getImage()),
               contentDescription = "",
               contentScale = ContentScale.Crop
           )
           Spacer(modifier = Modifier.size(15.dp))
           Column(modifier = Modifier.padding(25.dp,0.dp).weight(0.4f)){
               Text(
                   text = place.getTitle(),
                   color = Color.Black,
                   fontWeight = FontWeight.W700,
                   fontSize = MaterialTheme.typography.h4.fontSize,
                   maxLines = 1
               )
               Spacer(modifier = Modifier.size(15.dp))
               Text(
                   text = place.getDescription(),
                   color = Color.Black,
                   fontWeight = FontWeight.W500,
                   fontSize = MaterialTheme.typography.h6.fontSize,
               )
               Spacer(modifier = Modifier.size(15.dp))
           }
       }
    }
}