package org.project.healthykids.screens.registrations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natighajiyev.common.colors.PrimaryColors
import healthykids.composeapp.generated.resources.Res
import healthykids.composeapp.generated.resources.email_label
import healthykids.composeapp.generated.resources.login_button
import healthykids.composeapp.generated.resources.login_title
import healthykids.composeapp.generated.resources.forgot_password_question
import healthykids.composeapp.generated.resources.forgot_password_link
import healthykids.composeapp.generated.resources.password_label
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.project.healthykids.common.AppFonts
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController
import healthykids.composeapp.generated.resources.unvisibility
import healthykids.composeapp.generated.resources.visibility
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource
import org.project.healthykids.common.MessageDisplayer
import org.project.healthykids.navigation.Navigation
import org.project.healthykids.screens.registrations.contract.RegistrationContract
import org.project.healthykids.screens.registrations.contract.RegistrationViewModel

//TODO: Update actions based on viewModel and navController
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    messageDisplayer: MessageDisplayer,
    viewModel: RegistrationViewModel,
    navController: NavController,
) {
    val state = viewModel.state

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit){
        viewModel.effect.collectLatest { effect ->

            when(effect) {
                is RegistrationContract.Effect.Navigate -> {
                    state.value.result?.let {
                        if (it.resultCode in 200..299)
                            navController.navigate(Navigation.HomeNav.EntryPoint)
                    }
                }

                is RegistrationContract.Effect.WrongInputFormat -> {
                    messageDisplayer.showToast("Wrong email or password format!")
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 24.dp)

    ) {

        Box(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(Res.string.login_title),
                style = TextStyle(
                    fontSize = 27.sp,
                    fontFamily = FontFamily(Font(AppFonts.Montserrat_Bold)),
                    color = PrimaryColors.Primary900,
                    textAlign = TextAlign.Center
                )
            )
        }


        Column(
            modifier = Modifier
                .weight(0.5f)
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

            Spacer(Modifier.height(10.dp))


            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(Res.string.password_label)) },
                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (passwordVisible)
                        painterResource(Res.drawable.visibility)
                    else
                        painterResource(Res.drawable.unvisibility)

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(painter = icon, contentDescription = null, tint = PrimaryColors.Primary900)
                    }
                },
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
        }


        Column(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    viewModel.onEvent(RegistrationContract.Intent.Login(email, password))
                },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColors.Primary900),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(Res.string.login_button),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(AppFonts.Montserrat_Bold)),
                        color = PrimaryColors.Primary100,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Spacer(Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(Res.string.forgot_password_question),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = stringResource(Res.string.forgot_password_link),
                    color = PrimaryColors.Primary900,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        textDecoration = TextDecoration.Underline
                    ),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.clickable(onClick = {
                        //TODO: Add click action onFinish
                        navController.navigate(Navigation.RegistrationNav.ForgotPassword)
                    })
                )
            }
        }
    }
}
