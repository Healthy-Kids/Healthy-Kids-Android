package org.project.healthykids.screens.main.ProfileScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import healthykids.composeapp.generated.resources.ic_arrow_drop_down
import org.jetbrains.compose.resources.painterResource

@Composable
fun PersonalDataScreen(
    currentTab: String = "Profile",
    onTabClick: (String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    var gender by remember { mutableStateOf("") }
    var grade by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var school by remember { mutableStateOf("") }

    val genders = listOf("Девочка", "Мальчик")
    val grades = (1..11).map { "$it класс" }
    val schools = listOf("Школа №1", "Школа №2", "Школа №3", "Гимназия №5", "Лицей №10")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryColors.BackgroundColor)
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    text = "Личные данные",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColors.Primary900
                )
                Text(
                    text = "Заполните данные о вашем ребенке",
                    fontSize = 14.sp,
                    color = PrimaryColors.Primary900
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .background(PrimaryColors.Primary900, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("+", fontSize = 32.sp, color = Color.White)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Добавить фото",
                    fontSize = 12.sp,
                    color = PrimaryColors.Primary900
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Inputs
        CustomTextField(value = name, onValueChange = { name = it }, placeholder = "Имя")
        Spacer(modifier = Modifier.height(12.dp))
        CustomTextField(value = surname, onValueChange = { surname = it }, placeholder = "Фамилия")
        Spacer(modifier = Modifier.height(12.dp))
        CustomDropdown(value = gender, onValueChange = { gender = it }, placeholder = "Пол", items = genders)
        Spacer(modifier = Modifier.height(12.dp))
        CustomDropdown(value = grade, onValueChange = { grade = it }, placeholder = "Класс", items = grades)
        Spacer(modifier = Modifier.height(12.dp))

        // Height + Weight row
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(modifier = Modifier.weight(1f)) {
                CustomNumberField(
                    value = height,
                    onValueChange = { if (it.length <= 3) height = it.filter { ch -> ch.isDigit() } },
                    placeholder = "Рост",
                    suffix = " см"
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                CustomNumberField(
                    value = weight,
                    onValueChange = { if (it.length <= 3) weight = it.filter { ch -> ch.isDigit() } },
                    placeholder = "Вес",
                    suffix = " кг"
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        CustomTextField(value = phone, onValueChange = { phone = it }, placeholder = "+994 __ ___ __ __")
        Spacer(modifier = Modifier.height(12.dp))
        SearchableDropdown(value = school, onValueChange = { school = it }, placeholder = "Школа", items = schools)

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PrimaryOutlinedButton(text = "Сохранить", onClick = { /* TODO */ })
            PrimaryOutlinedButton(text = "Добавить ребёнка", onClick = { /* TODO */ })
        }
    }
}

@Composable
fun PrimaryOutlinedButton(
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White,
            contentColor = PrimaryColors.Primary900
        ),
        border = BorderStroke(1.dp, PrimaryColors.Primary900)
    ) {
        Text(text, fontSize = 16.sp, color = PrimaryColors.Primary900)
    }
}

@Composable
fun CustomTextField(value: String, onValueChange: (String) -> Unit, placeholder: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = PrimaryColors.Primary900) },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(PrimaryColors.Primary100, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryColors.Primary900,
            unfocusedBorderColor = PrimaryColors.Primary900,
            focusedTextColor = PrimaryColors.Primary900,
            unfocusedTextColor = PrimaryColors.Primary900
        )
    )
}

@Composable
fun CustomNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    suffix: String? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = PrimaryColors.Primary900) },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(PrimaryColors.Primary100, RoundedCornerShape(12.dp)),
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryColors.Primary900,
            unfocusedBorderColor = PrimaryColors.Primary900,
            focusedTextColor = PrimaryColors.Primary900,
            unfocusedTextColor = PrimaryColors.Primary900
        ),
        suffix = {
            if (!suffix.isNullOrEmpty()) {
                Text(
                    text = suffix,
                    color = PrimaryColors.Primary900,
                    fontSize = 14.sp
                )
            }
        }
    )
}

@Composable
fun CustomDropdown(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    items: List<String>
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            placeholder = { Text(placeholder, color = PrimaryColors.Primary900) },
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_drop_down),
                        contentDescription = null,
                        tint = PrimaryColors.Primary900,
                        modifier = Modifier.size(16.dp)
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(PrimaryColors.Primary100, RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryColors.Primary900,
                unfocusedBorderColor = PrimaryColors.Primary900,
                focusedTextColor = PrimaryColors.Primary900,
                unfocusedTextColor = PrimaryColors.Primary900
            )
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(PrimaryColors.Primary100)
                .fillMaxWidth()
        ) {
            items.forEach {
                DropdownMenuItem(
                    text = { Text(it, color = PrimaryColors.Primary900) },
                    onClick = {
                        onValueChange(it)
                        expanded = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp) // menyu elementləri də eyni hündürlükdə olsun
                )
            }
        }
    }
}

@Composable
fun SearchableDropdown(value: String, onValueChange: (String) -> Unit, placeholder: String, items: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    val filteredItems = items.filter { it.contains(searchQuery, ignoreCase = true) }

    Box {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            placeholder = { Text(placeholder, color = PrimaryColors.Primary900) },
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_drop_down),
                        contentDescription = null,
                        tint = PrimaryColors.Primary900,
                        modifier = Modifier.size(16.dp)
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(PrimaryColors.Primary100, RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryColors.Primary900,
                unfocusedBorderColor = PrimaryColors.Primary900,
                focusedTextColor = PrimaryColors.Primary900,
                unfocusedTextColor = PrimaryColors.Primary900
            )
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(PrimaryColors.Primary100)
                .fillMaxWidth()
        ) {
            // Search input
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Поиск...", color = PrimaryColors.Primary900) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                singleLine = true
            )

            filteredItems.forEach {
                DropdownMenuItem(
                    text = { Text(it, color = PrimaryColors.Primary900) },
                    onClick = {
                        onValueChange(it)
                        expanded = false
                    }
                )
            }
        }
    }
}
