package org.project.healthykids.screens.main.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.navigation.NavController
import com.natighajiyev.common.colors.PrimaryColors
import com.natighajiyev.domain.network.response.AllergyResponse
import healthykids.composeapp.generated.resources.Res
import healthykids.composeapp.generated.resources.allergy_search_placeholder
import healthykids.composeapp.generated.resources.allergy_subtitle
import healthykids.composeapp.generated.resources.allergy_title
import healthykids.composeapp.generated.resources.dropdown_content_description
import healthykids.composeapp.generated.resources.ic_arrow_drop_down
import healthykids.composeapp.generated.resources.ic_search
import healthykids.composeapp.generated.resources.search_content_description
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.project.healthykids.screens.main.profile.contract.ProfileContract
import org.project.healthykids.screens.main.profile.contract.ProfileViewModel

@Composable
fun AllergyScreen(
    modifier: Modifier = Modifier,
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

    val checkedAllergiesList = remember { mutableStateSetOf<AllergyResponse>() }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(PrimaryColors.BackgroundColor)
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        // Header
        item {
            Column {
                Text(
                    text = stringResource(Res.string.allergy_title),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColors.Primary900,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = stringResource(Res.string.allergy_subtitle),
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Search box
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    placeholder = { stringResource(Res.string.allergy_search_placeholder) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(Res.drawable.ic_search),
                            contentDescription = stringResource(Res.string.search_content_description),
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
            }
        }
        // Allergy items
        items(viewModel.state.value.allergiesList?.allergies ?: listOf()) { allergy ->
            AllergyItem(allergy) {
                checkedAllergiesList.add(allergy)
//                viewModel.onEvent()
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}


@Composable
fun AllergyItem(
    allergyResponse: AllergyResponse,
    onCheckAction: (AllergyResponse) -> Unit = {},
) {
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
        color = Color.White,
        border = BorderStroke(1.dp, PrimaryColors.Primary900),
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
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                checked = !checked
                                if (checked) onCheckAction.invoke(allergyResponse)
                            }
                            .background(
                                if (checked) PrimaryColors.Primary900 else Color.Transparent,
                                RoundedCornerShape(4.dp)
                            )
                            .border(2.dp, PrimaryColors.Primary900, RoundedCornerShape(4.dp))
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(allergyResponse.title, fontSize = 16.sp, color = Color.Black)
                }

                Icon(
                    painter = painterResource(Res.drawable.ic_arrow_drop_down),
                    contentDescription = stringResource(Res.string.dropdown_content_description),
                    tint = PrimaryColors.Primary900,
                    modifier = Modifier
                        .size(16.dp)
                        .rotate(rotation)
                )
            }

            AnimatedVisibility(expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = allergyResponse.description,
                    fontSize = 14.sp,
                    color = PrimaryColors.Primary900.copy(alpha = 0.8f)
                )
            }
        }
    }
}

