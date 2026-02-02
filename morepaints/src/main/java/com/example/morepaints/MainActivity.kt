package com.example.morepaints

import android.R.attr.path
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.morepaints.ui.theme.MorePaintsTheme
import kotlin.math.PI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MorePaintsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun DrawPath() {
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .background(Color(0xFFADD8E6))
    ) {
        val path = Path().apply {
            moveTo(0f, 0f)
            quadraticBezierTo(
                17.dp.toPx(), 66.dp.toPx(),
                100.dp.toPx(), 100.dp.toPx()
            )
            lineTo(33.dp.toPx(), 33.dp.toPx())
            quadraticBezierTo(20.dp.toPx(), 27.dp.toPx(), 0f, 0f)
            close()
        }
        drawPath(
            path = path,
            Color.Blue,
            style = Fill //Fill - по дефолту (можно также Stroke(width = 4f))
        )
    }
}

@Composable
fun DrawPoints() {
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .background(Color(0xFFADD8E6))
    ) {
        val height = size.height
        val width = size.width
        val points = mutableListOf<Offset>()

        for(x in 0..size.width.toInt()) {
            val y = (kotlin.math.sin(x * (2f * PI / width ))
                    * (height / 2 ) + (height / 2)).toFloat()
            points.add(Offset(x.toFloat(), y))
        }

        drawPoints(
            points = points,
            strokeWidth = 2f,
            pointMode = PointMode.Points,
            color = Color.Blue
        )
    }
}

@Composable
fun DrawImage() {

    val originalBitmap = ImageBitmap.imageResource(id = R.drawable.vacation)

    Canvas(
        modifier = Modifier
            .size(120.dp, 90.dp)
            .background(Color(0xFFADD8E6))
    ) {
        //---- трансформирую исходную картинку до размеров холста ----
        val scaledBitmap = Bitmap.createScaledBitmap(
            originalBitmap.asAndroidBitmap(),   // Преобразуем в Android Bitmap
            size.width.toInt(),             // Желаемая ширина (в пикселях)
            size.height.toInt(),           // Желаемая высота
            true                               // Фильтрация для плавности
        )
        val image: ImageBitmap = scaledBitmap.asImageBitmap()
        //------------------------------------------------------------
        // Другой способ (с помощью withTransform) не прокатил!!!!!
        drawImage(
            image = image,
            topLeft = Offset.Zero,
            colorFilter = ColorFilter.tint(
                color = Color(0xADFFAA2E),
                blendMode = BlendMode.ColorBurn
            )
        )
    }
}

@Composable
fun DrawText() {
    val colorList: List<Color> = listOf(
        Color.Black,
        Color.Blue, Color.Yellow, Color.Red, Color.Green, Color.Magenta
    )
    val textMeasurer = rememberTextMeasurer()
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = 50.sp,
                fontWeight = FontWeight.ExtraBold,
                brush = Brush.verticalGradient(colors = colorList)
            )
        ) {
            append("Text Drawing")
        }
    }

    Canvas(
        modifier = Modifier
            .size(300.dp, 140.dp)
            .background(Color(0xFFADD8E6))
    ) {
        val dimensions = textMeasurer.measure(annotatedText)

        drawText(
            textMeasurer = textMeasurer,
            text = annotatedText,
            topLeft = Offset.Zero)

       drawRect(
            brush = Brush.horizontalGradient(colors = colorList),
            size = dimensions.size.toSize(),
           topLeft = Offset(x = 0.dp.toPx(), y = 60.dp.toPx())
        )

        drawText(
            textMeasurer = textMeasurer,
            text = annotatedText,
            topLeft = Offset(x = 0.dp.toPx(), y = 60.dp.toPx())
        )
    }
}


@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DrawPath()

        Spacer(modifier = Modifier.height(20.dp))

        DrawPoints()

        Spacer(modifier = Modifier.height(20.dp))

        DrawImage()

        Spacer(modifier = Modifier.height(20.dp))

        DrawText()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MorePaintsTheme {
        MainScreen()
    }
}