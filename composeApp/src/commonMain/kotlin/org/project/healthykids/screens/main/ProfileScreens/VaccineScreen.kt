package org.project.healthykids.screens.main.ProfileScreens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
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
import healthykids.composeapp.generated.resources.ic_search
import healthykids.composeapp.generated.resources.ic_arrow_drop_down
import org.jetbrains.compose.resources.painterResource

@Composable
fun VaccineScreen(
    modifier: Modifier = Modifier,
    currentTab: String = "Profile",
    onTabClick: (String) -> Unit
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
            text = "Прививка",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryColors.Primary900,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Какую прививку вы делали своему ребенку?",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Search box
        OutlinedTextField(
            value = "",
            onValueChange = { },
            placeholder = { Text("Поиск Прививка") },
            leadingIcon = {
                Icon(
                    painter = painterResource(Res.drawable.ic_search),
                    contentDescription = "Search",
                    modifier = Modifier.size(16.dp),
                    tint = PrimaryColors.Primary900
                )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                unfocusedBorderColor = PrimaryColors.Primary900,
                focusedBorderColor = PrimaryColors.Primary900,
                cursorColor = PrimaryColors.Primary900
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Allergy items
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            repeat(5) {
                VaccineItem(title = "пример", description = "Это пример текста для раскрытого элемента")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Navigation
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
fun VaccineItem(title: String, description: String) {
    var expanded by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "arrowRotation"
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Color.White, // 🔹 Beyaz arka plan
        border = BorderStroke(1.dp, PrimaryColors.Primary900), // 🔹 Primary900 border
        shadowElevation = 1.dp
    ) {
        Column(
            modifier = Modifier
                .clickable { expanded = !expanded }
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Checkbox görünümü
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { checked = !checked }
                            .background(
                                if (checked) PrimaryColors.Primary900 else Color.Transparent,
                                RoundedCornerShape(4.dp)
                            )
                            .border(2.dp, PrimaryColors.Primary900, RoundedCornerShape(4.dp))
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(title, fontSize = 16.sp, color = Color.Black)
                }

                Icon(
                    painter = painterResource(Res.drawable.ic_arrow_drop_down),
                    contentDescription = "Dropdown",
                    tint = PrimaryColors.Primary900,
                    modifier = Modifier
                        .size(16.dp)
                        .rotate(rotation)
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = PrimaryColors.Primary900.copy(alpha = 0.8f)
                )
            }
        }
    }
}

