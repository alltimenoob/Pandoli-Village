package com.example.myapplication.counter

import android.content.Intent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.sp
import com.example.myapplication.MainActivity2
import com.example.myapplication.R
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
@Composable
fun CounterApp() {
    var context = LocalContext.current

    Scaffold(

        topBar = {
            TopAppBar(title = { Text("Vallabh Vidhyanagar", textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 27.sp,
                modifier = Modifier.padding(0.dp,0.dp,10.dp,0.dp),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W500) }, backgroundColor = Color.White, elevation = 0.dp,

                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ },) {
                        Icon(painterResource(id = R.mipmap.menu), contentDescription = null , Modifier.size(30.dp))
                    }
                },
                actions = {
                } )
        },
        floatingActionButton = {

        },
        content = {

            Column(modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())){

                val imageDetails = listOf("Shashtri Medan","Mota Bazar","Nana Bazar")
                var image by remember {
                    mutableStateOf(0)
                }
                LaunchedEffect(Unit) {
                    while(true) {
                        delay(5.seconds)
                        image = (image + 1) % 3
                    }
                }

                Row(modifier = Modifier
                    .height(100.dp)
                    .padding(20.dp, 0.dp) , horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                    Text(text = "Home", textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontSize = 27.sp,
                        modifier = Modifier.padding(0.dp,0.dp,10.dp,0.dp),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W500)

                    Text(text = "News", textAlign = TextAlign.Center,
                        color = Color.LightGray,
                        fontSize = 27.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W300,
                        modifier = Modifier.clickable { context.startActivity(Intent(context,MainActivity2::class.java)) }
                        )

                    Text(text = "Videos", textAlign = TextAlign.Center,
                        color = Color.LightGray,
                        fontSize = 27.sp,
                        modifier = Modifier.padding(10.dp,0.dp),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W300)

                    Text(text = "Places", textAlign = TextAlign.Center,
                        color = Color.LightGray,
                        fontSize = 27.sp,
                        modifier = Modifier.padding(10.dp,0.dp),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W300)
                }

                Card(elevation = 10.dp,
                    modifier = Modifier
                        .padding(20.dp, 0.dp)
                        .weight(1f)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(5),
                    content = {
                        Image( 
                            painterResource(when(image){
                                1 -> R.drawable.vvn_1
                                2 -> R.drawable.vvn_2
                                else -> {
                                    R.drawable.vvn_3
                                }
                            }),
                            contentDescription = "Vidhya Nagar",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    image = (image + 1) % 3
                                },
                            )

                        Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize())
                        {
                            Text(text = imageDetails[image],
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontSize = 30.sp,
                                modifier = Modifier.padding(10.dp,20.dp),
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.W300
                            )
                            Row(modifier = Modifier.padding(10.dp,10.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)){
                                Button(onClick = { /*TODO*/ },
                                    Modifier
                                        .height(3.dp)
                                        .width(15.dp),colors = ButtonDefaults.buttonColors(backgroundColor = when(image){
                                            0 -> Color.White
                                            else -> Color.LightGray
                                        })) {}
                                Button(onClick = { /*TODO*/ },
                                    Modifier
                                        .height(3.dp)
                                        .width(15.dp),colors = ButtonDefaults.buttonColors(backgroundColor = when(image){
                                        1 -> Color.White
                                        else -> Color.LightGray
                                    })) {}
                                Button(onClick = { /*TODO*/ },
                                    Modifier
                                        .height(3.dp)
                                        .width(15.dp),colors = ButtonDefaults.buttonColors(backgroundColor = when(image){
                                        2 -> Color.White
                                        else -> Color.LightGray
                                    })) {}
                            }
                        }
                    }
                )

                Column(modifier = Modifier
                    .weight(1f)
                    .padding(20.dp, 20.dp, 10.dp, 20.dp), verticalArrangement = Arrangement.spacedBy(10.dp))
                {
                    Text(text = "About Vidhyanagar", textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontSize = 27.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold)

                    Text(text = "It has roots in a piece of classical Latin literature from 45 BC,words, consecteturIt has roots in a piece of classical Latin literature from 45 BC,words, consecteturIt has roots in a piece of classical Latin literature from 45 BC,words, consecteturIt has roots in a piece of classical Latin literature from 45 BC,words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                    lineHeight = 20.sp)
                }
            }
        },
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxSize()
    ) 
}


