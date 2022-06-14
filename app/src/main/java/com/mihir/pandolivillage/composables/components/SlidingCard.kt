package com.mihir.pandolivillage.composables.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mihir.pandolivillage.composables.model.Card


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SlidingCard(list: List<Card>,modifier: Modifier , content : ()->Unit){
    LazyRow(modifier = modifier) {
        item {
            list.forEach {
                Card(
                    modifier = Modifier
                        .height(400.dp)
                        .padding(20.dp,20.dp,20.dp,20.dp)
                        .fillParentMaxWidth(0.75f),
                    shape = RoundedCornerShape(25.dp),
                    elevation = 15.dp
                ) {
                    SlidingCardItem(
                        image = it.getImage(),
                        title = it.getTitle(),
                        description = it.getDescription()
                    )
                }
            }
        }
    }
}

@Composable
fun SlidingCardItem(image: Int, title: String,description : String){
    Image(
        painter = painterResource(id = image),
        contentDescription = title ,
        contentScale = ContentScale.Crop
    )

    Column(
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                     Color.Black.copy(alpha = 0.0f),
                     Color.Black.copy(alpha = 0.3f),
                     Color.Black.copy(alpha = 1f)

                )
        )),
        verticalArrangement = Arrangement.Bottom
    ) {

        Text(modifier = Modifier.padding(25.dp,0.dp),
            text = title , color = Color.White ,
            fontWeight = FontWeight.W500 ,
            fontSize =  MaterialTheme.typography.h4.fontSize )
        Text(modifier = Modifier.padding(25.dp,25.dp),
            text = description , color = Color.White,
            fontWeight = FontWeight.W300 ,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = 4
        )
    }


}


@Composable
@Preview
fun PreviewCard(){
    //SlidingCard()
}