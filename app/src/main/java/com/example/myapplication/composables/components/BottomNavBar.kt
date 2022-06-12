package com.example.myapplication.composables.components

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.composables.model.Screens

// The Holder
@Composable
fun BottomNavBar(navController: NavController,list : List<Screens>) {

    val currentId  by navController.currentBackStackEntryAsState()

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        , verticalAlignment = Alignment.CenterVertically
        , horizontalArrangement = Arrangement.SpaceEvenly
        ){

        list.forEach{ item ->
            BottomNavItem(item = item, isSelected = item.route==currentId?.destination?.route ){
                navController.backQueue.forEach{
                    print(it.destination.route+" ")
                }
                println()


                navController.navigate(item.route){
                    popUpTo(Screens.Videos.route){
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }

            }
        }
    }
    
}


//The Layout
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomNavItem(item : Screens, isSelected : Boolean, onClick : ()->Unit  )
{
    Box(modifier = Modifier
        .clip(RoundedCornerShape(20.dp))
        .clickable(onClick = onClick)
        .background(if (isSelected) Color.Black else Color.White)
        .padding(10.dp))
    {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Icon(painter = painterResource(id = item.icon) ,
                contentDescription = "Image" ,
                tint = if (isSelected) Color.White else Color.Black
            )

            Spacer(modifier = Modifier.size(5.dp))

            AnimatedVisibility(visible = isSelected) {
                fadeIn() with fadeOut()
                Text(item.title , color = if (isSelected) Color.White else Color.Black)
            }
        }
    }

}



