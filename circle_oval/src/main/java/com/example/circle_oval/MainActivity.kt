/*
    Рисуем круги, овалы, тени, градиенты, дуги
 */
package com.example.circle_oval

import android.os.Bundle
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.circle_oval.ui.theme.MainScreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun DrawCircle() {
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .background(Color(0xFFADD8E6))
    ) {
        drawCircle(
            color = Color.Blue,
            center = center,
            radius = 50.dp.toPx()
        )
    }
}

@Composable
fun DrawOval() {
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .background(Color(0xFFADD8E6))
    ) {
        val canvasWidth = size.width
        val canvasHeigth = size.height
        drawOval(
            color = Color.Blue,
            topLeft = Offset(x = 8.dp.toPx(), y = 30.dp.toPx()),
            size = Size(
                width = canvasWidth - 16.dp.toPx(),
                height = canvasHeigth / 2 - 16.dp.toPx()
            ),
            style = Stroke(width = 2.dp.toPx())
        )
    }
}

@Composable
fun RectGradientFill() {
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .background(Color(0xFFADD8E6))
    ) {
        val canvasSize = size
        val colorList: List<Color> = listOf(Color.Red, Color.Blue,
            Color.Magenta, Color.Yellow, Color.Green, Color.Cyan)
        val brush = Brush.horizontalGradient(
            colors = colorList,
            startX = 0f,
            endX = 100.dp.toPx(),
            tileMode = TileMode.Repeated
        )
        drawRect(
            brush = brush,
            size = canvasSize
        )
    }
}
@Composable
fun RadialFill() {
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .background(Color(0xFFADD8E6))
    ) {
        val canvasSize = size
        val radius = 50.dp.toPx()
        val colorList: List<Color> = listOf(Color.Red, Color.Blue,
            Color.Magenta, Color.Yellow, Color.Green, Color.Cyan)
        val brush = Brush.radialGradient(
            colors = colorList,
            center = center,
            radius = radius,
            tileMode = TileMode.Repeated
        )
        drawCircle(
            brush = brush,
            center = center,
            radius = radius
        )
    }
}
@Composable
fun ShadowCircle() {
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .background(Color(0xFFADD8E6))
    ) {
        val radius = 50.dp.toPx()
        val colorList: List<Color> = listOf(Color.Blue, Color.Black)
        val brush = Brush.horizontalGradient(
            colors = colorList,
            startX = 0f,
            endX = 100.dp.toPx(),
            tileMode = TileMode.Repeated
        )
        drawCircle(
            brush = brush,
            center = center,
            radius = radius
        )
    }
}
@Composable
fun DrawArc() {
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .background(Color(0xFFADD8E6))
    ) {
        drawArc(
            Color.Blue,
            topLeft = Offset(-50.dp.toPx(), -50.dp.toPx()),
            startAngle = 0f,
            sweepAngle = 90f,
            useCenter = true,
            size = Size(100.dp.toPx(), 100.dp.toPx())
        )
        drawArc(
            Color.Blue,
            topLeft = Offset(+50.dp.toPx(), +50.dp.toPx()),
            startAngle = 180f,
            sweepAngle = 90f,
            useCenter = true,
            size = Size(100.dp.toPx(), 100.dp.toPx())
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
        DrawCircle()

        Spacer(modifier = Modifier.height(20.dp))

        DrawOval()

        Spacer(modifier = Modifier.height(20.dp))

        RectGradientFill()

        Spacer(modifier = Modifier.height(20.dp))

        RadialFill()

        Spacer(modifier = Modifier.height(20.dp))

        ShadowCircle()

        Spacer(modifier = Modifier.height(20.dp))

        DrawArc()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreenTheme {
        MainScreen()
    }
}