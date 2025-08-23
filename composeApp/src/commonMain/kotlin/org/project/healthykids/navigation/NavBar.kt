package org.project.healthykids.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natighajiyev.common.colors.Grayscale50
import com.natighajiyev.common.colors.PrimaryColors.Primary900
import com.natighajiyev.common.colors.White
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


data class NavItemModel(
    val image: DrawableResource,
    val title: String
)

//TODO: Change animation
@Composable
fun NavBar(
    selectedIndex: MutableState<Int>,
    list: MutableList<NavItemModel> = mutableListOf(
        // TODO: Add bottom nav graph items here

    )
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .background(Grayscale50, RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            list.forEachIndexed { index, icon ->
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(11f)
                        .clickable { selectedIndex.value = index },
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        modifier = if (selectedIndex.value == index)
                            Modifier.offset(y = (-8).dp)
                        else
                            Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    if (selectedIndex.value == index) Primary900
                                    else Color.Transparent,
                                    shape = CircleShape
                                )
                                .size(45.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(resource = icon.image),
                                contentDescription = "content",
                                modifier = Modifier.size(25.dp),
                                tint = if (selectedIndex.value == index) White else Primary900
                            )
                        }

                        if (icon.title.isNotEmpty()){
                            AnimatedVisibility(visible = (selectedIndex.value == index)) {
                                Text(
                                    text = icon.title,
                                    modifier = Modifier,
                                    color = Primary900,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}