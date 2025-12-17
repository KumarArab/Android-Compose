package com.example.birthdaycard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.birthdaycard.ui.theme.BirthdayCardTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BirthdayCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeQuadrant()
//                    TaskManager(title = "All tasks completed", subtitle = "Nice work!")
//                    ComposeArticle(title = "Jetpack Compose tutorial", para1 ="Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs." , para2 ="In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.")
//                    GreetingImage("Happy Birthday Sam!","from Bae")
                }
            }
        }
    }
}

@Composable
fun GreetingText(message: String, from:String, modifier: Modifier = Modifier) {
   Column(
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally,
       modifier = modifier.padding(8.dp)
   ) {

       Text(
           text = message,
           fontSize = 100.sp,
           lineHeight = 116.sp,
           textAlign = TextAlign.Center
       )
       Text(
           text = from,
           fontSize = 36.sp,
           modifier = Modifier
               .padding(16.dp)
               .align(alignment = Alignment.CenterHorizontally)
       )
   }
}


@Composable
fun GreetingImage(message: String, from: String,modifier: Modifier = Modifier){
val image = painterResource(id = R.drawable.androidparty)
    Box(modifier) {
        Image(painter = image, contentDescription = "Happy birthday background",contentScale = ContentScale.Crop, alpha = 0.8F)
        GreetingText(message = message, from = from,modifier = modifier
            .fillMaxSize()
            .padding(8.dp))
    }
}
@Composable
fun ComposeArticle(title:String, para1: String, para2:String, modifier: Modifier = Modifier){
    val image = painterResource(id = R.drawable.bg_compose_background)
    Column(){
        Image(painter = image, contentDescription = "Hero image",contentScale = ContentScale.FillWidth)
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = para1,
            modifier = modifier.padding(horizontal = 16.dp ),
            textAlign = TextAlign.Justify
        )
        Text(
            text = para2,
            modifier = modifier.padding(16.dp ),
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun TaskManager(title:String, subtitle:String, modifier: Modifier = Modifier){
    val image = painterResource(id = R.drawable.ic_task_completed)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = image, contentDescription = "Hero image",contentScale = ContentScale.FillWidth)
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = subtitle,
            modifier = modifier.padding(horizontal = 16.dp ),
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun ComposeQuadrant(modifier: Modifier = Modifier){
    Column(
        verticalArrangement = Arrangement.Center,

    ) {
        Row(
            modifier = modifier.weight(0.5F)
        ) {

                Box(
                    modifier
                        .fillMaxSize(1F)
                        .background(Color(0xFFEADDFF))
                        .weight(0.5F,true)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = modifier.fillMaxSize(1F).padding(16.dp)
                    ) {
                        Text(
                            text = "Text composable",
                            fontWeight = FontWeight.Bold,
                            modifier = modifier.padding(bottom = 16.dp),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Displays text and follows the recommended Material Design guidelines.",
                            textAlign = TextAlign.Center
                        )
                    }
                }

            Box(
                modifier
                    .fillMaxSize(1F)
                    .background(Color(0xFFD0BCFF)
                    )
                    .weight(0.5F,true)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.fillMaxSize(1F).padding(16.dp)
                ) {
                    Text(
                        text = "Image composable",
                        fontWeight = FontWeight.Bold,
                        modifier = modifier.padding(bottom = 16.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Creates a composable that lays out and draws a given Painter class object.",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        Row(
            modifier = modifier.weight(0.5F)
        ) {

            Box(
                modifier
                    .fillMaxSize(1F)
                    .background(Color(0xFFB69DF8)
                    )
                    .weight(0.5F,true)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.fillMaxSize(1F).padding(16.dp)
                ) {
                    Text(
                        text = "Row composable",
                        fontWeight = FontWeight.Bold,
                        modifier = modifier.padding(bottom = 16.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "A layout composable that places its children in a horizontal sequence.",
                        textAlign = TextAlign.Center
                    )
                }
            }

            Box(
                modifier
                    .fillMaxSize(1F)
                    .background(Color(0xFFF6EDFF))
                    .weight(0.5F,true)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.fillMaxSize(1F).padding(16.dp)
                ) {
                    Text(
                        text = "Column composable",
                        fontWeight = FontWeight.Bold,
                        modifier = modifier.padding(bottom = 16.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "A layout composable that places its children in a vertical sequence.",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }



    }
}



@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    BirthdayCardTheme {
        ComposeQuadrant()
//TaskManager(title = "All tasks completed", subtitle = "Nice work!")
//        ComposeArticle(title = "Jetpack Compose tutorial", para1 ="Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs." , para2 ="In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.")
//        GreetingImage(stringResource(R.string.happy_birthday_sam),"from Bae")
    }
}