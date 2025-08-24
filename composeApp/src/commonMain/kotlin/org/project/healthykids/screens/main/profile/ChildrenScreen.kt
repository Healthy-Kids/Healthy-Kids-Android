package org.project.healthykids.screens.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.natighajiyev.common.colors.PrimaryColors
import com.natighajiyev.domain.network.response.ChildSelectionListResponse
import com.natighajiyev.domain.network.response.ChildrenSelectionListResponse
import healthykids.composeapp.generated.resources.Res
import healthykids.composeapp.generated.resources.ic_walkthrough_1
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource
import org.project.healthykids.screens.main.profile.contract.ProfileContract
import org.project.healthykids.screens.main.profile.contract.ProfileViewModel

@Composable
fun ChildrenScreen(
    viewModel: ProfileViewModel,
    navController: NavController,
) {
    val state = viewModel.state
    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is ProfileContract.Effect.Navigate -> {
                    state.value.result?.let {
//                        if (it.resultCode in 200..299)
//                            navController.navigate(Navigation.HomeNav.EntryPoint)
                    }
                }
            }
        }
    }

    var selectedChild by remember { mutableStateOf<ChildSelectionListResponse?>(null) }

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
        viewModel.state.value.childrenList?.children?.forEach { child ->
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
                    text = child.name,
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
