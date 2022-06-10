package com.example.myapplication.composables.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.composables.components.CardList
import com.example.myapplication.composables.constants.Card
import com.example.myapplication.helpers.PlacesViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PlacesScreen(navController: NavController,sharedViewModel: PlacesViewModel) {
    
    var list = listOf(
        Card(
            R.drawable.vvn_1,"Mota Bazar","This is great place for egg items ." +
            " Best center for the egg items in anand. Place is clean , offers good seating facilities" +
            " and also offers great food. Every weekend i go there to eat food. There are special items" +
            " for whole family and also cloddrinks" +
            " .u can take away food also and raw eggs also u can take away . Big thumb from me"),
        Card(
            R.drawable.vvn_2,"Nana Bazar","I've been here both as a customer and as a delivery person. " +
                "The owner is good, and is friendly. This place is known for the taste. Mostly college students " +
                "visit here or order online. This place is available for online orders. But it's better if you " +
                "dine in, the food tastes better there. The prices are moderate. It depends on what you order. " +
                "Overall, it's a good place for food."),
        Card(
            R.drawable.vvn_3,"Shashtri Medan","The Food was good, but not great. We ordered Mexican and Italian dishes." +
                " But all their sauces tasted same. You would not be able to tell apart a lasagna and an enchilada. " +
                "Ambience is nice. The food is a bit overpriced given the quality.")
    )


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
                   text = "Places",
                   color = Color.Black,
                   fontWeight = FontWeight.W700,
                   fontSize = MaterialTheme.typography.h4.fontSize,
                   maxLines = 1
               )
           }

       },
       modifier = Modifier.fillMaxSize()
   ){
        CardList(list = list){
            sharedViewModel.addPlaces(card = it)
            navController.navigate("Place")
        }
    }
}