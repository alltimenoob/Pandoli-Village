package com.example.myapplication.composables.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapplication.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
sealed class News(val title: String,val image: Int,val link: String) {
    object Sandesh : News(
        title = "Sandesh",
        image = R.drawable.sandesh,
        link = "https://www.sandesh.com/epaper/kheda"
    )


    object Divyabhaskar : News(
        title = "Divya Bhaskar",
        image = R.drawable.divyabhaskar,
        link = "https://epaper.divyabhaskar.co.in/charotar-anand/anand/18/"+
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMy"))
                +"/0/1/"
    )

    object Gujaratsamachar : News(
        title = "Gujarat Samachar",
        image = R.drawable.gujaratsamachar,
        link = "http://www.epapergujaratsamachar.com/nd/gujaratsamachar.php?isid=GUJARAT_KHE_"+
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yMMdd"))
    )

    object Navgujrat : News(
        title = "NavGujarat Samay",
        image = R.drawable.navgujarat,
        link = "https://epaper.navgujaratsamay.com/3496925/Madhya-Gujarat-Samay/"
    )




}