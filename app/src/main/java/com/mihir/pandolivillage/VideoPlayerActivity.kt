package com.mihir.pandolivillage

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.mihir.pandolivillage.ui.theme.MyApplicationTheme
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class VideoPlayerActivity : ComponentActivity() {
    lateinit var ytPlayer : YouTubePlayerView
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        val videoId =  intent.extras?.get("link")

        setContent {
            MyApplicationTheme {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = {
                        YouTubePlayerView(it).apply {
                            ytPlayer = this
                            enableAutomaticInitialization = false

                            initialize(object : AbstractYouTubePlayerListener() {

                                override fun onReady(youTubePlayer: YouTubePlayer) {
                                    super.onReady(youTubePlayer)
                                    youTubePlayer.loadVideo(videoId.toString(), 0f)
                                }

                            })
                        }
                    },
                    update = {
                        it.enterFullScreen()
                    }
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ytPlayer.release()
    }
}