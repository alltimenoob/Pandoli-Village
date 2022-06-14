package com.mihir.pandolivillage.composables.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.mihir.pandolivillage.R
import com.mihir.pandolivillage.ui.theme.Background
@Composable
fun UsefulLinksScreen(
    navController : NavController
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    )
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(modifier = Modifier
                .height(50.dp)
                .padding(20.dp, 20.dp, 20.dp, 0.dp)
                .background(Background) ,
                onClick = { navController.popBackStack()}) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Hamburger Menu"
                )
            }
            Text(
                modifier = Modifier
                    .height(50.dp)
                    .padding(20.dp, 20.dp, 20.dp, 0.dp)
                    .background(Background),
                text = "Useful Links",
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontWeight = FontWeight.W600,
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        
        Column(
            modifier = Modifier.padding(20.dp,0.dp)
        ){
            val list = HashMap<String,String>()
            list["Any ROR"]="https://anyror.gujarat.gov.in/LandRecordRural.aspx"
            list["E Milkat"]="https://e-milkat.gujarat.gov.in/"
            list["Pan Card"]="https://www.onlineservices.nsdl.com/paam/endUserRegisterContact.html"
            list["E Aadhaar"]="https://uidai.gov.in/"
            list["Passport"]="https://www.passportindia.gov.in/AppOnlineProject/welcomeLink"
            list["IRCTC"]="https://www.irctc.co.in/nget/train-search"

            list.forEach{
                Links(text = it.key){
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.value)))
                }
            }
        }

    }
}

@Composable
fun Links(text : String,onClick : ()->Unit)
{
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
        fontWeight =  MaterialTheme.typography.headlineSmall.fontWeight,
        textDecoration = TextDecoration.Underline,
        color = Color.Blue
    )
    Spacer(modifier = Modifier.height(20.dp))
}