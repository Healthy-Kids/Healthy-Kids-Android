package org.project.healthykids.screens.main.ProfileScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import org.jetbrains.compose.resources.painterResource
import healthykids.composeapp.generated.resources.Home_filled
import healthykids.composeapp.generated.resources.Profile
import healthykids.composeapp.generated.resources.Profile_filled
import healthykids.composeapp.generated.resources.Healthy
import healthykids.composeapp.generated.resources.Healthy_filled
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onLogoutClick: () -> Unit,
    onMyChildrenClick: () -> Unit,
    onVaccinesClick: () -> Unit,
    onExaminationsClick: () -> Unit,
    onAllergyClick: () -> Unit,
    onPersonalDataClick: () -> Unit,
    onLanguagesClick: () -> Unit,
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Привет,",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColors.Primary900
                )
                Text(
                    text = "Natiq reis!",
                    fontSize = 18.sp,
                    color = PrimaryColors.Primary900
                )
            }
            Surface(
                modifier = Modifier.size(40.dp),
                color = PrimaryColors.Primary900,
                shape = RoundedCornerShape(50)
            ) {}
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Main cards
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                InfoCard("Мои дети", modifier = Modifier.weight(1f), onClick = onMyChildrenClick)
                InfoCard("Прививки", modifier = Modifier.weight(1f), onClick = onVaccinesClick)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                InfoCard(title = "Обследования", modifier = Modifier.weight(1f), onClick = onExaminationsClick)
                InfoCard(title = "Аллергия", modifier = Modifier.weight(1f), onClick = onAllergyClick)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Other items
        SettingItem(title = "Личные данные", onClick = onPersonalDataClick)
        Spacer(modifier = Modifier.height(12.dp))
        SettingItem(title = "Языки", onClick = onLanguagesClick)

        Spacer(modifier = Modifier.height(16.dp))

        // Logout button
        Button(
            onClick = { onLogoutClick() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Text(
                text = "Выйти из аккаунта",
                color = Color.Black,
                fontSize = 16.sp
            )
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
fun InfoCard(title: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Surface(
        modifier = modifier
            .height(200.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(12.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}

@Composable
fun SettingItem(title: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable { onClick() }, // 🔹 clickable elədim
        shape = RoundedCornerShape(8.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, fontSize = 14.sp, color = Color.Black)

            Text(
                text = "⋮",
                fontSize = 28.sp,
                color = PrimaryColors.Primary900,
                modifier = Modifier.padding(end = 2.dp)
            )
        }
    }
}

@Composable
fun BottomNavItem(
    label: String,
    isSelected: Boolean,
    defaultIcon: DrawableResource,
    filledIcon: DrawableResource,
    onClick: () -> Unit
) {
    val icon: DrawableResource = if (isSelected) filledIcon else defaultIcon

    Box(
        modifier = Modifier
            .clickable { onClick() }
            .then(
                if (isSelected) Modifier
                    .background(
                        color = Color(0xFF004D40),
                        shape = RoundedCornerShape(50)
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp)
                else Modifier.padding(6.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = label,
                modifier = Modifier.size(25.dp)
            )
            if (isSelected) {
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = label,
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
        }
    }
}

