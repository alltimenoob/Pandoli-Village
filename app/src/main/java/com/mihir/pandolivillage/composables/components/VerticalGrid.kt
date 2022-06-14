package com.mihir.pandolivillage.composables.components


import com.mihir.pandolivillage.composables.model.News
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mihir.pandolivillage.ui.theme.Background

@Composable
fun ListCard(list : List<News>, onClick : (news: News)->Unit){
    LazyVerticalGrid(
        columns =  GridCells.Fixed(2),
        modifier = Modifier
            .background(Background)
            .fillMaxSize()
    ) {
        itemsIndexed(list){ _, item ->

            androidx.compose.material.Card(
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                .height(200.dp)
                .width(200.dp)
                .padding(20.dp)
                .background(Background)
                .clickable { onClick(item) },
                elevation = 15.dp
            ) {
                ListCardItem(news = item)
            }
        }
    }
}


@Composable
fun ListCardItem(news : News){

    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = news.image),
            contentDescription = "news.description",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .weight(6f)
                .fillMaxWidth()
        )
        Box(modifier = Modifier
            .weight(2f)
            .fillMaxWidth()
            .background(Color.Black.copy(0.85f)),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = news.title,
                color = Color.White,
                fontWeight = FontWeight.W700,
                fontSize = MaterialTheme.typography.body1.fontSize
            )
        }
    }
}