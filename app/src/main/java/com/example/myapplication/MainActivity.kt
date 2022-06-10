package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebBackForwardList
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.composables.BottomNavBar
import com.example.myapplication.composables.constants.Screens
import com.example.myapplication.composables.screens.*
import com.example.myapplication.helpers.PlacesViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MyApplicationTheme {

                val navController = rememberNavController()

                val list = listOf(Screens.Home, Screens.News, Screens.Videos, Screens.Places)

                val sharedPlacesViewModel : PlacesViewModel = viewModel()

                Scaffold(
                    bottomBar = {   BottomNavBar(navController = navController,list) })
                {
                    Box(modifier = Modifier.padding(it)){
                        NavHost(navController = navController, startDestination = Screens.Home.route ){
                            composable(route = Screens.Home.route){
                                HomeScreen(navController = navController)
                            }
                            composable(route = Screens.News.route){
                                NewsScreen(navController = navController)
                            }
                            composable(route = Screens.Videos.route){
                                VideosScreen(navController = navController)
                            }
                            composable(route = Screens.Places.route){
                                PlacesScreen(navController = navController, sharedViewModel = sharedPlacesViewModel)
                            }
                            composable(route = "Menu")
                            {
                                MenuScreen(navController = navController)
                            }
                            composable(route = "NewsPaper" + "/{link}", arguments = listOf(
                                navArgument("link"){
                                    type = NavType.StringType
                                    nullable = true
                                }))
                            { navStackEntry->
                                NewsPaperScreen(link = navStackEntry.arguments?.getString("link"))
                            }
                            composable(route = "Place")
                            {
                                PlaceScreen(sharedPlacesViewModel)
                            }
                        }
                    }
                }

            }

        }
    }
}


