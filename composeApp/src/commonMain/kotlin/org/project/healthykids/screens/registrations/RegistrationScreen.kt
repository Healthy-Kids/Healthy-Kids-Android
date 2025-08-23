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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natighajiyev.common.colors.PrimaryColors
import healthykids.composeapp.generated.resources.Res
import healthykids.composeapp.generated.resources.consent_text
import healthykids.composeapp.generated.resources.email_label
import healthykids.composeapp.generated.resources.first_name_label
import healthykids.composeapp.generated.resources.have_account
import healthykids.composeapp.generated.resources.last_name_label
import healthykids.composeapp.generated.resources.login_button
import healthykids.composeapp.generated.resources.phone_label
import healthykids.composeapp.generated.resources.register_button
import healthykids.composeapp.generated.resources.registration_title
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.project.healthykids.common.AppFonts
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.parameter.parameterSetOf
import org.project.healthykids.common.MessageDisplayer
import org.project.healthykids.navigation.Navigation
import org.project.healthykids.screens.registrations.contract.RegistrationContract
import org.project.healthykids.screens.registrations.contract.RegistrationViewModel


@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    messageDisplayer: MessageDisplayer,
    navController: NavController,
    viewModel: RegistrationViewModel,
) {
    val state = viewModel.state

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var consent by remember { mutableStateOf(false) }

    LaunchedEffect(Unit){
        viewModel.effect.collectLatest { effect ->

            when(effect) {
                is RegistrationContract.Effect.Navigate -> {
                    state.value.result?.let {
                        if (it.resultCode in 200..299)
                            navController.navigate(Navigation.RegistrationNav.Login)
                    }
                }

                is RegistrationContract.Effect.WrongInputFormat -> {
                    messageDisplayer.showToast("Wrong input format!")
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
                text = stringResource(Res.string.registration_title),
                style = MaterialTheme.typography.headlineSmall.copy(
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
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text(stringResource(Res.string.first_name_label)) },
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
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text(stringResource(Res.string.last_name_label)) },
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
                value = phone,
                onValueChange = { phone = it },
                label = { Text(stringResource(Res.string.phone_label)) },
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

            Spacer(Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = consent,
                    onCheckedChange = { consent = it },
                    colors = CheckboxDefaults.colors(checkedColor = PrimaryColors.Primary900)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    stringResource(Res.string.consent_text),
                    style = MaterialTheme.typography.bodySmall
                )
            }
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
                    viewModel.onEvent(RegistrationContract.Intent.CreateAccount(
                        email = email,
                        fullname = "$firstName $lastName",
                        phone = phone,
                        password = password     // TODO: Why password is not implemented?
                    ))
                },
                enabled = consent,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColors.Primary900),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(Res.string.register_button),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(AppFonts.Montserrat_Bold)),
                        color = PrimaryColors.Primary100,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
            Spacer(Modifier.height(12.dp))
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){

                Text(stringResource(Res.string.have_account),
                    fontSize = 15.sp,

                    textAlign = TextAlign.Center)
                Text(
                    text = stringResource(Res.string.login_button),
                    color = PrimaryColors.Primary900,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        textDecoration = TextDecoration.Underline
                    ),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.clickable(onClick = {
                        navController.navigate(Navigation.RegistrationNav.Login)
                    })
                )
            }
        }
    }}

