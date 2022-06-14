package com.mihir.pandolivillage

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mihir.pandolivillage.composables.components.BottomNavBar
import com.mihir.pandolivillage.composables.model.Screens
import com.mihir.pandolivillage.composables.model.YoutubeVideo
import com.mihir.pandolivillage.composables.screens.*
import com.mihir.pandolivillage.helpers.PlacesViewModel
import com.mihir.pandolivillage.helpers.VideosViewModel


class HomeActivity : ComponentActivity() {

    lateinit var sharedPreferences: SharedPreferences

    @OptIn(ExperimentalComposeUiApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        setContent {
                val navController = rememberNavController()

                val screenlist = listOf(Screens.Home, Screens.News, Screens.Videos, Screens.Places)

                val sharedPlacesViewModel : PlacesViewModel = viewModel()
                val sharedVideosViewModel : VideosViewModel = viewModel()

                sharedPreferences = this.getSharedPreferences("MihirShared", MODE_PRIVATE)

                val videoList = listOf(object : YoutubeVideo("Mvz3_9O4p4s","TV9 Gujarati Live"){},
                    object : YoutubeVideo("nyd-xznCpJc","ABP NEWS LIVE: 24*7"){},
                    object : YoutubeVideo("VmW8VgNOzpo","Sandesh News Live"){},
                    object : YoutubeVideo("qfrocHBy6RQ","Republic Bharat LIVE"){})

                sharedVideosViewModel.setList(videoList)
                Scaffold(
                    bottomBar = {   BottomNavBar(navController = navController,screenlist) })
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
                                VideosScreen(sharedVideosViewModel)
                            }
                            composable(route = Screens.Places.route){
                                PlacesScreen(navController = navController, sharedViewModel = sharedPlacesViewModel)
                            }
                            composable(route = "Menu")
                            {
                                MenuScreen(navController = navController, sharedPreferences = sharedPreferences)
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
                            composable(route = "Profile")
                            {
                                ProfileScreen(navController)
                            }
                            composable(route = "UsefulLinks")
                            {
                                UsefulLinksScreen(navController)
                            }
                        }
                    }
                }



        }
    }
}


