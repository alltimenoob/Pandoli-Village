package com.mihir.pandolivillage.composables.model

import com.mihir.pandolivillage.R

sealed class Screens(val route: String, val title: String, val icon:Int){
    object Home : Screens(
        route = "home",
        title = "Home",
        icon = R.drawable.ic_home
    )

    object News : Screens(
        route = "news",
        title = "News",
        icon = R.drawable.ic_news
    )

    object Videos : Screens(
        route = "videos",
        title = "Videos",
        icon = R.drawable.ic_video
    )

    object Places : Screens(
        route = "places",
        title = "Places",
        icon = R.drawable.ic_places
    )
}