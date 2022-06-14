package com.mihir.pandolivillage.composables.components
import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mihir.pandolivillage.VideoPlayerActivity
import com.mihir.pandolivillage.composables.model.YoutubeVideo
import com.mihir.pandolivillage.ui.theme.Background


@Composable
fun YoutubeList(list : List<YoutubeVideo>?) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item{
            list?.forEach{
                YoutubeItem(it)
            }
        }
    }
}

@SuppressLint("ResourceType")
@Composable
fun YoutubeItem(video: YoutubeVideo){

    val context  = LocalContext.current

    Card(
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .padding(35.dp)
            .height(250.dp),
        elevation = 15.dp
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            Box(
                modifier = Modifier.weight(0.8f),
                contentAlignment = Alignment.Center
            ){
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = rememberAsyncImagePainter("https://img.youtube.com/vi/"+video.getId()+"/mqdefault.jpg"),
                    contentDescription = "" ,
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    modifier = Modifier.background(Color.Black),
                    onClick = { context.startActivity(Intent(context,VideoPlayerActivity::class.java).putExtra("link",video.getId())) }
                ) {
                    Image(
                        modifier = Modifier.size(50.dp),
                        painter = painterResource(id = com.pierfrancescosoffritti.androidyoutubeplayer.R.drawable.ayp_ic_play_36dp ) ,
                        contentDescription = " ")
                }
            }
            Box(
                modifier = Modifier.weight(0.2f).padding(15.dp,0.dp),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = video.getTitle(),
                    color = Color.Black,
                    fontWeight = FontWeight.W700,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    textAlign = TextAlign.Justify
                )
            }

        }


    }




}

