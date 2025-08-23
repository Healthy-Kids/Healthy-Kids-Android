package org.project.healthykids.screens.registrations
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.natighajiyev.common.colors.PrimaryColors
import healthykids.composeapp.generated.resources.Res
import healthykids.composeapp.generated.resources.forgot_password_title
import healthykids.composeapp.generated.resources.otp_description
import healthykids.composeapp.generated.resources.forgot_password_next
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.project.healthykids.common.AppFonts
import org.project.healthykids.common.MessageDisplayer
import org.project.healthykids.navigation.Navigation
import org.project.healthykids.screens.registrations.contract.RegistrationContract
import org.project.healthykids.screens.registrations.contract.RegistrationViewModel
import kotlin.also

@Composable
fun OtpScreen(
    messageDisplayer: MessageDisplayer,
    navController: NavController,
    viewModel: RegistrationViewModel,
) {
    val state = viewModel.state

    val otpLength = 6
    var otp by remember { mutableStateOf(MutableList(otpLength) { "" }) }
    val focusRequesters = List(otpLength) { FocusRequester() }

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
                text = stringResource(Res.string.otp_description),
                fontSize = 16.sp,
                color = PrimaryColors.Primary900,
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .weight(0.45f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(otpLength) { index ->
                    OutlinedTextField(
                        value = otp[index],
                        onValueChange = { value: String ->
                            if (value.length <= 1 && value.all { it.isDigit() }) {
                                otp = otp.toMutableList().also { it[index] = value }

                                if (value.isNotEmpty() && index < otpLength - 1) {
                                    focusRequesters[index + 1].requestFocus()
                                }

                                if (value.isEmpty() && index > 0) {
                                    focusRequesters[index - 1].requestFocus()
                                }
                            }
                        },
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .size(50.dp)
                            .focusRequester(focusRequesters[index]),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryColors.Primary900,
                            unfocusedBorderColor = PrimaryColors.Primary900,
                            cursorColor = PrimaryColors.Primary900,
                            focusedTextColor = PrimaryColors.Primary900,
                            unfocusedTextColor = PrimaryColors.Primary900
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
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
                onClick = { viewModel.onEvent(RegistrationContract.Intent.CheckOtp(otp)) },
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
