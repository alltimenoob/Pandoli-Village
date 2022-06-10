package com.example.myapplication.composables.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.example.myapplication.ui.theme.Background
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.composables.components.SlidingCard
import com.example.myapplication.composables.constants.Card

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    var list = listOf(Card(R.drawable.vvn_1,"Mota Bazar","This is great place for egg items ." +
            " Best center for the egg items in anand. Place is clean , offers good seating facilities" +
            " and also offers great food. Every weekend i go there to eat food. There are special items" +
            " for whole family and also cloddrinks" +
            " .u can take away food also and raw eggs also u can take away . Big thumb from me"),
        Card(R.drawable.vvn_2,"Nana Bazar","I've been here both as a customer and as a delivery person. " +
                "The owner is good, and is friendly. This place is known for the taste. Mostly college students " +
                "visit here or order online. This place is available for online orders. But it's better if you " +
                "dine in, the food tastes better there. The prices are moderate. It depends on what you order. " +
                "Overall, it's a good place for food."),
        Card(R.drawable.vvn_3,"Shashtri Medan","The Food was good, but not great. We ordered Mexican and Italian dishes." +
                " But all their sauces tasted same. You would not be able to tell apart a lasagna and an enchilada. " +
                "Ambience is nice. The food is a bit overpriced given the quality.")
    )
    var card = Card(R.drawable.news,"Shashtri Medan","The Food was good, but not great. We ordered Mexican and Italian dishes." +
    " But all their sauces tasted same. You would not be able to tell apart a lasagna and an enchilada. " +
            "Ambience is nice. The food is a bit overpriced given the quality.")

    Scaffold(topBar = {
        Box(modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .background(Color.White),
            contentAlignment = Alignment.Center
        )
        {
            Image(
                modifier = Modifier
                    .height(40.dp)
                    .padding(20.dp,0.dp)
                    .align(Alignment.CenterStart)
                    .clickable {
                        navController.navigate("Menu")
                    },
                painter = painterResource(id = R.drawable.ic_menu),
                contentScale = ContentScale.Crop,
                contentDescription = "Hamburger Menu"
            )
            Text(
                text = "Home",
                color = Color.Black,
                fontWeight = FontWeight.W700,
                fontSize = androidx.compose.material.MaterialTheme.typography.h4.fontSize,
                maxLines = 1
            )
        }


    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .verticalScroll(rememberScrollState())
                .padding(it)
        )
        {



            SlidingCard(list = list ,modifier = Modifier
                .fillMaxWidth()
                .background(Background)){
            }
/*
        Text(
            text= "com.example.myapplication.composables.constants.News",
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp, 10.dp, 10.dp)
                .height(50.dp),
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )

        com.example.myapplication.composables.constants.News(card = card,navController)

        Text(
            text= "Places",
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 20.dp, 10.dp, 10.dp)
                .height(50.dp),
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )

        com.example.myapplication.composables.constants.News(card = card,navController)

        Text(
            text= "Videos",
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 20.dp, 10.dp, 10.dp)
                .height(50.dp),
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )

        com.example.myapplication.composables.constants.News(card = card,navController)
*/
            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}

/*
@Composable
fun com.example.myapplication.composables.constants.News(card : Card,navController: NavController){
    Card(
        modifier = Modifier
            .height(200.dp)
            .padding(20.dp, 0.dp, 20.dp, 0.dp)
            .clickable { navController.navigate("com.example.myapplication.composables.constants.News") },
        shape = RoundedCornerShape(10.dp),
        elevation = 0.dp
    ){
        Row(modifier = Modifier
            .padding(12.dp)
            .background(Color.White)
        ) {
            Box(modifier = Modifier
                .width(150.dp)
                .clip(RoundedCornerShape(10.dp)))
            {
                Image(
                    painter = painterResource(id = card.getImage()),
                    contentDescription = card.getDescription() ,
                    contentScale = ContentScale.Crop
                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start) {
                Text(
                    text = card.getTitle(),
                    color = Color.Black,
                    fontWeight = FontWeight.W700,
                    fontSize = androidx.compose.material.MaterialTheme.typography.subtitle1.fontSize,
                    maxLines = 1
                )
                Text(
                    text = card.getDescription(),
                    color = Color.Gray,
                    fontWeight = FontWeight.W300,
                    fontSize = androidx.compose.material.MaterialTheme.typography.subtitle1.fontSize,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }

}



*/
