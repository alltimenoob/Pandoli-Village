package com.mihir.pandolivillage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            {
                Image(
                    modifier = Modifier.size(200.dp),
                    painter = painterResource(id = R.drawable.village),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.size(15.dp))
                Text(
                    text = "Pandoli",
                    fontWeight = FontWeight.W700,
                    fontSize = androidx.compose.material.MaterialTheme.typography.h4.fontSize,
                )
            }

           Handler().postDelayed({
               if(this.getSharedPreferences("MihirShared" , Context.MODE_PRIVATE).getString("username","default").equals("default"))
               {
                   this.startActivity(Intent(this,LoginActivity::class.java))
               }
               else
               {
                   this.startActivity(Intent(this,HomeActivity::class.java))
               }
               finish()
           },
               2000L)
        }
    }
}