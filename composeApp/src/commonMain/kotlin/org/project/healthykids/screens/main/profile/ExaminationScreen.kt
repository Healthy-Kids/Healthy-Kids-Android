package org.project.healthykids.screens.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natighajiyev.common.colors.PrimaryColors
import healthykids.composeapp.generated.resources.Res
import healthykids.composeapp.generated.resources.Home_filled
import healthykids.composeapp.generated.resources.Profile
import healthykids.composeapp.generated.resources.Profile_filled
import healthykids.composeapp.generated.resources.Healthy
import healthykids.composeapp.generated.resources.Healthy_filled
import healthykids.composeapp.generated.resources.ic_download
import healthykids.composeapp.generated.resources.ic_file
import org.jetbrains.compose.resources.painterResource

@Composable
fun ExaminationsScreen(
    modifier: Modifier = Modifier,
    currentTab: String = "Profile",
    onTabClick: (String) -> Unit,
    onAddClick: () -> Unit,
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PrimaryColors.BackgroundColor)
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Обследования",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryColors.Primary900
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Add Examination Button
        OutlinedButton(
            onClick = { onAddClick() },
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = PrimaryColors.Primary900
            ),
            border = ButtonDefaults.outlinedButtonBorder()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_download),
                    contentDescription = "Download",
                    tint = PrimaryColors.Primary900,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Добавить обследования",
                    color = PrimaryColors.Primary900,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // List of Examinations
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            repeat(3) {
                ExaminationItem(
                    title = "Анализ 12223",
                    filesCount = 2,
                    onClick = { onItemClick("Анализ 12223") }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Navigation (aynı kalıyor)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0xFFC7D6B8),
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                label = "",
                isSelected = currentTab == "Healthy",
                defaultIcon = Res.drawable.Healthy_filled,
                filledIcon = Res.drawable.Healthy,
                onClick = { onTabClick("Healthy") }
            )
            BottomNavItem(
                label = "",
                isSelected = currentTab == "Home",
                defaultIcon = Res.drawable.Home_filled,
                filledIcon = Res.drawable.Home_filled,
                onClick = { onTabClick("Home") }
            )
            BottomNavItem(
                label = "Profile",
                isSelected = currentTab == "Profile",
                defaultIcon = Res.drawable.Profile_filled,
                filledIcon = Res.drawable.Profile,
                onClick = { onTabClick("Profile") }
            )
        }
    }
}

@Composable
fun ExaminationItem(
    title: String,
    filesCount: Int,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Sol ikon kutusu
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(PrimaryColors.Primary900, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_file),
                        contentDescription = "File",
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        title,
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        "$filesCount файла",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }
            }

            // Sağdaki 3 nokta
            Text(
                text = "⋮",
                fontSize = 28.sp,
                color = PrimaryColors.Primary900,
                modifier = Modifier.padding(end = 4.dp)
            )
        }
    }
}

