package org.project.healthykids.screens.main.ProfileScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natighajiyev.common.colors.PrimaryColors
import healthykids.composeapp.generated.resources.Res
import healthykids.composeapp.generated.resources.ic_walkthrough_1
import org.jetbrains.compose.resources.painterResource

@Composable
fun ChildsScreen(
    currentTab: String = "Profile",
    onTabClick: (String) -> Unit
) {
    var selectedChild by remember { mutableStateOf("Назрин") }

    val children = listOf("Назрин", "Эля", "Али")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryColors.BackgroundColor)
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Мои дети",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryColors.Primary900,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Children list
        children.forEach { child ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Child photo
                Image(
                    painter = painterResource(Res.drawable.ic_walkthrough_1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.Gray, CircleShape)
                )

                Spacer(modifier = Modifier.width(12.dp))

                // Name
                Text(
                    text = child,
                    fontSize = 16.sp,
                    color = PrimaryColors.Primary900,
                    modifier = Modifier.weight(1f)
                )

                // RadioButton
                RadioButton(
                    selected = selectedChild == child,
                    onClick = { selectedChild = child },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = PrimaryColors.Primary900,
                        unselectedColor = Color.White
                    )
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Add child button
        PrimaryOutlinedButton(
            text = "Добавить ребенка",
            onClick = { /* TODO: navigate add child */ }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Bottom Navigation (sənin əvvəlki kimi)
        // BottomNavBar(currentTab = currentTab, onTabClick = onTabClick)
    }
}
