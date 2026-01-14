package com.example.artgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artgallery.ui.theme.ArtGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtWorkView("Android")
                }
            }
        }
    }
}

data class Artwork(
    @DrawableRes val imageRes: Int,
    val title: String,
    val artistName: String,
    val year: Int
)

@Composable
fun ArtWorkView(name: String, modifier: Modifier = Modifier) {

    val artworkList = listOf(
        Artwork(
            R.drawable.colorful_20abstract_20artwork_952b616f_5af3_4b37_9f92_188d915f2fba_jpg,
            "The Starry Night",
            "Vincent van Gogh",
            1889
        ),
        Artwork(R.drawable.dp130155_jpg, "Mona Lisa", "Leonardo da Vinci", 1503),
        Artwork(R.drawable.il_570xn_3672949762_25c2_jpg, "The Scream", "Edvard Munch", 1893),
        Artwork(
            R.drawable.pxl_20240302_085804049,
            "Girl with a Pearl Earring",
            "Johannes Vermeer",
            1665
        ),
        Artwork(
            R.drawable.handmade_2fdownscaled_2fh_ereudc2go5d_2000x2000__71869,
            "The Persistence of Memory",
            "Salvador Dalí",
            1931
        ),
        Artwork(
            R.drawable.img_5870_original,
            "Girl with a Pearl Earring",
            "Johannes Vermeer",
            1665
        ),

        )

    var currentIndex by remember { mutableStateOf(0) }




    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center // Keeps the image centered in the "empty" space
        ) {
            ArtworkImage(image = artworkList[currentIndex].imageRes)
        }
        ArtworkDescription(
            title = artworkList[currentIndex].title,
            artistName = artworkList[currentIndex].artistName,
            year = artworkList[currentIndex].year
        )
        ArtworkActionable(
            onBack = {
                currentIndex = backward(currentIndex, artworkList.size);
            },
            onForward = {
                currentIndex = forward(currentIndex, artworkList.size);
            }
        )
        Spacer(modifier = modifier.size(16.dp))

    }
}


@Composable
fun ArtworkImage(@DrawableRes image: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(width = 300.dp, height = 400.dp) // Fixed container size
            .shadow(
                elevation = 15.dp,
                ambientColor = Color.Black.copy(alpha = 0.3f),
                spotColor = Color.Black
            )
            .background(Color.LightGray)          // Optional: to see the container edge
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
                .shadow(
                    elevation = 8.dp,
                    spotColor = Color.Black,
                    ambientColor = Color.Black.copy(alpha = 0.3f),
                ),    // Tells image to fill the Box
            contentScale = ContentScale.Crop      // Crops the image to fill the space completely

        )
    }
}

@Composable
fun ArtworkDescription(
    title: String,
    artistName: String,
    year: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            modifier.padding(horizontal =16.dp),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            letterSpacing = 2.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            fontFamily = FontFamily.Cursive,

        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier.padding(horizontal = 20.dp)
,
                    horizontalArrangement = Arrangement.Center
        ) {
            Text(text = artistName, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Text(text = " (${year})", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun ArtworkActionable(onBack: () -> Unit, onForward: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(48.dp)
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = onBack,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(2.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF683933), // Background color
                contentColor = Color.White,          // Text/Icon color
                disabledContainerColor = Color.Gray, // Color when enabled = false
                disabledContentColor = Color.LightGray
            ),
        ) {
            Text(text = "Back")
        }

        Button(
            onClick = onForward,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(2.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF683933), // Background color
                contentColor = Color.White,          // Text/Icon color
                disabledContainerColor = Color.Gray, // Color when enabled = false
                disabledContentColor = Color.LightGray
            ),
        ) {
            Text(text = "Forward")
        }
    }
}

internal fun forward(currentIndex: Int, maxLength: Int): Int {
    return if (currentIndex < maxLength - 1) {
        currentIndex + 1;
    } else 0;
}

internal fun backward(currentIndex: Int, maxLength: Int): Int {
    return if (currentIndex > 0) {
        currentIndex - 1;
    } else maxLength - 1;
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtGalleryTheme {
        ArtWorkView("Android")
    }
}