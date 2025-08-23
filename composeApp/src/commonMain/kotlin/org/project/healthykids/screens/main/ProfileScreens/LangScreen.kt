package org.project.healthykids.screens.main.ProfileScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.natighajiyev.common.colors.PrimaryColors
import healthykids.composeapp.generated.resources.Res
import healthykids.composeapp.generated.resources.az
import healthykids.composeapp.generated.resources.en
import healthykids.composeapp.generated.resources.ru
import healthykids.composeapp.generated.resources.Home_filled
import healthykids.composeapp.generated.resources.Profile
import healthykids.composeapp.generated.resources.Profile_filled
import healthykids.composeapp.generated.resources.Healthy
import healthykids.composeapp.generated.resources.Healthy_filled
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun LangScreen(
    currentTab: String = "Profile",
    onTabClick: (String) -> Unit
) {
    var selectedLanguage by remember { mutableStateOf("az") }

    val languages = listOf(
        Triple("az", "Азербайджанский", Res.drawable.az),
        Triple("en", "Английский", Res.drawable.en),
        Triple("ru", "Русский", Res.drawable.ru)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryColors.BackgroundColor)
            .systemBarsPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Text(
            text = "Язык",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryColors.Primary900
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Language items
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            languages.forEach { (code, label, icon) ->
                LanguageItem(
                    label = label,
                    icon = icon,
                    isSelected = selectedLanguage == code,
                    onClick = { selectedLanguage = code }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // 🔹 Bottom Navigation
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0xFFC7D6B8),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp)
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
fun LanguageItem(label: String, icon: DrawableResource, isSelected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White, CircleShape)
                    .border(1.dp, PrimaryColors.Primary900, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = label,
                    modifier = Modifier.size(33.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = label,
                fontSize = 16.sp,
                color = PrimaryColors.Primary900
            )
        }

        // Right side circle (radio)
        Box(
            modifier = Modifier
                .size(20.dp)
                .border(2.dp, PrimaryColors.Primary900, CircleShape)
                .background(
                    if (isSelected) PrimaryColors.Primary900 else Color.Transparent,
                    CircleShape
                )
        )
    }
}
