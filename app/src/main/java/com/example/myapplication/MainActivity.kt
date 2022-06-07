package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.composables.BottomNavBar
import com.example.myapplication.composables.constants.Screens
import com.example.myapplication.composables.screens.*
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MyApplicationTheme {

                val navController = rememberNavController()

                val list = listOf(Screens.Home, Screens.News, Screens.Videos, Screens.Places)

                Scaffold(
                    bottomBar = {   BottomNavBar(navController = navController,list) })
                {
                    Box(modifier = Modifier.padding(it)){
                        NavHost(navController = navController, startDestination = Screens.Home.route ){
                            composable(route = Screens.Home.route){
                                HomeScreen()
                            }
                            composable(route = Screens.News.route){
                                NewsScreen()
                            }
                            composable(route = Screens.Videos.route){
                                PlacesScreen()
                            }
                            composable(route = Screens.Places.route){
                                VidesScreen()
                            }
                        }
                    }
                }

            }

        }
    }
}


@Preview(showBackground = false)
@Composable
fun DefaultPreview() {

}