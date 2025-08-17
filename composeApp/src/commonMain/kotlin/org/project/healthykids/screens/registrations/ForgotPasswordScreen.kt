package org.project.healthykids.screens.registrations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natighajiyev.common.colors.PrimaryColors
import healthykids.composeapp.generated.resources.Res
import healthykids.composeapp.generated.resources.email_label
import healthykids.composeapp.generated.resources.forgot_password_title
import healthykids.composeapp.generated.resources.forgot_password_description
import healthykids.composeapp.generated.resources.forgot_password_next
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.project.healthykids.common.AppFonts

@Composable
fun ForgotPasswordScreen(
    onSendClick: (String) -> Unit,
) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryColors.BackgroundColor)
            .padding(horizontal = 24.dp, vertical = 24.dp)
    ) {


        Column(
            modifier = Modifier
                .weight(0.25f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(Res.string.forgot_password_title),
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryColors.Primary900,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(Res.string.forgot_password_description),
                fontSize = 16.sp,
                color = PrimaryColors.Primary900,
                textAlign = TextAlign.Center
            )
        }


        Column(
            modifier = Modifier
                .weight(0.45f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(Res.string.email_label)) },
                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryColors.Primary900,
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = PrimaryColors.Primary900,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = PrimaryColors.Primary900,
                    focusedTextColor = PrimaryColors.Primary900,
                    unfocusedTextColor = PrimaryColors.Primary900
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
        Column(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { onSendClick(email) },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColors.Primary900),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    text = stringResource(Res.string.forgot_password_next),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(AppFonts.Montserrat_Bold)),
                        color = PrimaryColors.Primary100,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }

        }
    }
}
