package org.project.healthykids.screens.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.natighajiyev.common.colors.PrimaryColors
import com.natighajiyev.domain.network.request.FileRequest
import healthykids.composeapp.generated.resources.Res
import healthykids.composeapp.generated.resources.ic_download
import healthykids.composeapp.generated.resources.ic_file
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform.getKoin
import org.project.healthykids.manager.FileManager
import org.project.healthykids.screens.main.profile.contract.ProfileViewModel

@Composable
fun ExaminationsScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val fileManager: FileManager = getKoin().get()
    val filesList = remember { mutableStateListOf<FileRequest?>() }


    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(PrimaryColors.BackgroundColor)
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        // Header
        item {
            Text(
                text = "Обследования",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryColors.Primary900
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Add Examination Button
            OutlinedButton(
                onClick = {
                    // add file button
                    // navigate to file add screen
                    scope.launch {
                        val file = fileManager.pickFile()
                        println("File picked: ${file?.size}")
                        filesList.add(file)
                    }
                },
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
        }

        items(filesList) { file ->
            // TODO: Should be added spacing
            ExaminationItem(
                title = file?.title ?: "Анализ 12223",
                filesCount = 2,
                onClick = {
                    //TODO: File click action
                }
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

