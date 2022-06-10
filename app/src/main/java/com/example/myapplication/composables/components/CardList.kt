package com.example.myapplication.composables.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.composables.constants.Card
import com.example.myapplication.ui.theme.Background

@Composable
fun CardList(list : List<Card>,onClick:  (card : Card)->Unit){
    LazyColumn {
        item {
            list.forEach{ card->
                CardListItem(card = card, onClick = onClick)
            }
        }
    }
}


@Composable
fun CardListItem(card: Card, onClick: (card : Card)->Unit){
    androidx.compose.material.Card(
        modifier = Modifier
            .background(Background)
            .fillMaxSize()
            .padding(25.dp),
        elevation = 15.dp,
        shape = RoundedCornerShape(25.dp)
    ){
        Column(
            modifier = Modifier
                .height(300.dp)
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .weight(5f),
                painter = painterResource(card.getImage()),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Box(modifier = Modifier
                .fillMaxSize()
                .padding(15.dp, 0.dp)
                .weight(1f)
            ){
                Text(
                    text = card.getTitle(),
                    color = Color.Black,
                    fontWeight = FontWeight.W700,
                    fontSize = MaterialTheme.typography.h6.fontSize
                )
            }
            
            Button(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp, 10.dp)
                    .weight(2f)
                    .clip(RoundedCornerShape(15.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black.copy(0.85f)),
                contentPadding = PaddingValues(
                    top = 5.dp,
                    bottom = 5.dp,
                    start = 5.dp,
                    end = 5.dp
                ),
                onClick = { onClick(card) }
            ) {
                Text(
                    text = "Learn More",
                    color = Color.White,
                    fontWeight = FontWeight.W500,
                    fontSize = MaterialTheme.typography.body1.fontSize
                )
            }

        }

    }
}