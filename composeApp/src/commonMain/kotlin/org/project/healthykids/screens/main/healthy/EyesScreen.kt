package org.project.healthykids.screens.main.healthy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import healthykids.composeapp.generated.resources.Res
import healthykids.composeapp.generated.resources.ic_walkthrough_2
import org.jetbrains.compose.resources.painterResource

@Composable
fun EyesScreen(
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE7F0E1))
            .systemBarsPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(24.dp))

        // Title
        Text(
            text = "Тест на зрение",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(Modifier.height(24.dp))

        // Description
        Text(
            text = "Держите телефон на расстоянии вытянутой руки\nи отметьте строчки которые вы не видите",
            fontSize = 20.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            lineHeight = 18.sp
        )

        Spacer(Modifier.height(24.dp))

        // Kangaroo image
        Image(
            painter = painterResource(Res.drawable.ic_walkthrough_2),
            contentDescription = "Eyes Test",
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(350.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(Modifier.height(24.dp))

        // Time info
        Text(
            text = "время теста: 10 минут",
            fontSize = 20.sp,
            color = Color.Black
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = "в конце теста вас ждут результаты",
            fontSize = 15.sp,
            color = Color.DarkGray
        )

        Spacer(Modifier.height(48.dp))

        // Next button
        Button(
            onClick = onNextClick,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004D40)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Next", fontSize = 16.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}
