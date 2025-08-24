package org.project.healthykids.screens.main.healthy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.natighajiyev.common.colors.PrimaryColors
import healthykids.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import healthykids.composeapp.generated.resources.Healthy
import healthykids.composeapp.generated.resources.Healthy_filled
import healthykids.composeapp.generated.resources.Home_filled
import healthykids.composeapp.generated.resources.Profile
import healthykids.composeapp.generated.resources.Profile_filled
import healthykids.composeapp.generated.resources.blue_bag
import healthykids.composeapp.generated.resources.pink_bag
import healthykids.composeapp.generated.resources.girl_run
import healthykids.composeapp.generated.resources.boy_run
import healthykids.composeapp.generated.resources.eye_test
import healthykids.composeapp.generated.resources.iq_test
import healthykids.composeapp.generated.resources.nervous_test


import org.jetbrains.compose.resources.DrawableResource

@Composable
fun HealthyScreen(
    modifier: Modifier = Modifier,
    currentTab: String = "Healthy",
    onTabClick: (String) -> Unit,
    onPortfolioClick: () -> Unit,
    onSportClick: () -> Unit,
    onEyesClick: () -> Unit,
    onFoodClick: () -> Unit,
    onIqClick: () -> Unit,
    onNervousClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE7F0E1))
            .systemBarsPadding()
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Healthy",
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                }
                Surface(
                    modifier = Modifier.size(40.dp),
                    color = PrimaryColors.Primary900,
                    shape = RoundedCornerShape(50)
                ) {}
            }
            Spacer(Modifier.height(24.dp))



            HealthyDualImageCard(
                title = "Портфель",
                image1 = Res.drawable.blue_bag,
                image2 = Res.drawable.pink_bag,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                onClick = onPortfolioClick
            )

            Spacer(Modifier.height(12.dp))


            HealthyDualImageCard(
                title = "Спорт",
                image1 = Res.drawable.boy_run,
                image2 = Res.drawable.girl_run,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                onClick = onSportClick
            )

            Spacer(Modifier.height(16.dp))


            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    HealthyImageCard(
                        title = "Глаза",
                        image = Res.drawable.eye_test,
                        modifier = Modifier
                            .weight(1f)
                            .height(200.dp),
                        onClick = onEyesClick
                    )
                    HealthyImageCard(
                        title = "Еда",
                        image = Res.drawable.pink_bag, // TODO: food şəkilini qoy
                        locked = true,
                        modifier = Modifier
                            .weight(1f)
                            .height(200.dp),
                        onClick = onFoodClick
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    HealthyImageCard(
                        title = "IQ test",
                        image = Res.drawable.iq_test,
                        modifier = Modifier
                            .weight(1f)
                            .height(200.dp),
                        onClick = onIqClick
                    )
                    HealthyImageCard(
                        title = "Тест на нервы",
                        image = Res.drawable.nervous_test,
                        modifier = Modifier
                            .weight(1f)
                            .height(200.dp),
                        onClick = onNervousClick
                    )
                }
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFC7D6B8), shape = RoundedCornerShape(24.dp))
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                label = "Healthy",
                isSelected = currentTab == "Healthy",
                defaultIcon = Res.drawable.Healthy_filled,
                filledIcon = Res.drawable.Healthy,
                onClick = { onTabClick("Healthy") }
            )
            BottomNavItem(
                label = "Home",
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
fun HealthyImageCard(
    title: String,
    image: DrawableResource,
    modifier: Modifier = Modifier,
    locked: Boolean = false,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .height(150.dp)
            .clickable { if (!locked) onClick() },
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {

            Image(
                painter = painterResource(image),
                contentDescription = title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0x00073D3D),
                                Color(0xFF073D3D)
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            )


            Text(
                text = if (locked) "🔒 $title" else title,
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

@Composable
fun HealthyDualImageCard(
    title: String,
    image1: DrawableResource,
    image2: DrawableResource,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp),
                horizontalArrangement = Arrangement.spacedBy((-20).dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(image1),
                    contentDescription = title,
                    modifier = Modifier.size(150.dp),
                    contentScale = ContentScale.Fit
                )
                Image(
                    painter = painterResource(image2),
                    contentDescription = title,
                    modifier = Modifier.size(150.dp),
                    contentScale = ContentScale.Fit
                )
            }


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0x00073D3D), // transparent
                                Color(0xFF073D3D)  // dark green
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            )


            Text(
                text = title,
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
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
                    .background(color = Color(0xFF004D40), shape = RoundedCornerShape(50))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
                else Modifier.padding(6.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(icon),
                contentDescription = label,
                modifier = Modifier.size(25.dp)
            )
            if (isSelected) {
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = label, fontSize = 15.sp, color = Color.White)
            }
        }
    }
}
