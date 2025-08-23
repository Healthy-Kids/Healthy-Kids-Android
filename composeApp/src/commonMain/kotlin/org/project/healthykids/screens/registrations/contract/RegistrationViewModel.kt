package org.project.healthykids.screens.registrations.contract

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natighajiyev.domain.model.CrudModel
import com.natighajiyev.domain.model.UiResult
import com.natighajiyev.domain.network.request.CreateAccountRequest
import com.natighajiyev.domain.network.request.LoginRequest
import com.natighajiyev.domain.usecases.registration.CheckOtpUseCase
import com.natighajiyev.domain.usecases.registration.CreateAccountUseCase
import com.natighajiyev.domain.usecases.registration.LoginUseCase
import com.natighajiyev.domain.usecases.registration.ResetPasswordUseCase
import com.natighajiyev.domain.usecases.registration.SetNewPasswordUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.project.healthykids.screens.registrations.RegistrationScreen

class RegistrationViewModel(
    private val createAccountUseCase: CreateAccountUseCase,
    private val loginUseCase: LoginUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val checkOtpUseCase: CheckOtpUseCase,
    private val setNewPasswordUseCase: SetNewPasswordUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(RegistrationContract.State())
    val state: State<RegistrationContract.State> = _state

    private val _effect = Channel<RegistrationContract.Effect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(intent: RegistrationContract.Intent) {
        when (intent) {
            is RegistrationContract.Intent.Login -> login(intent.email, intent.password)
            is RegistrationContract.Intent.CreateAccount -> createAccount(
                intent.email,
                intent.password,
                intent.fullname,
                intent.phone
            )

            is RegistrationContract.Intent.ResetPassword -> resetPassword(intent.email)
            is RegistrationContract.Intent.CheckOtp -> checkOtp(intent.otp)
            is RegistrationContract.Intent.SetNewPassword -> setNewPassword(intent.newPassword)
        }
    }

    private fun postEffect(effect: RegistrationContract.Effect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }

    private fun login(email: String, password: String) {
        if (email.isEmpty() || email.endsWith(GMAIL_TAIL) || password.length < 8) {
            postEffect(RegistrationContract.Effect.WrongInputFormat(WRONG_FILLED_INPUT))
            return
        }

        loginUseCase.invoke(LoginRequest(email, password))
            .onEach { uiResult ->
                when (uiResult) {
                    is UiResult.Success -> {
                        _state.value =
                            _state.value.copy(
                                result = CrudModel(resultCode = 200, message = SUCCESS),
                                isLoading = false
                            )

                        postEffect(RegistrationContract.Effect.Navigate)
                    }

                    is UiResult.Error -> {
                        _state.value =
                            _state.value.copy(error = uiResult.message, isLoading = false)
                    }

                    is UiResult.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun createAccount(
        email: String,
        password: String,
        fullname: String,
        phone: String,
    ) {
        createAccountUseCase.invoke(CreateAccountRequest(fullname, phone, email, password))
            .onEach { uiResult ->
                when (uiResult) {
                    is UiResult.Success -> {
                        _state.value =
                            _state.value.copy(
                                result = CrudModel(resultCode = 200, message = SUCCESS),
                                isLoading = false
                            )
                    }

                    is UiResult.Error -> {
                        _state.value =
                            _state.value.copy(error = uiResult.message, isLoading = false)
                    }

                    is UiResult.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }


    private fun resetPassword(email: String) {
        resetPasswordUseCase.invoke(email)
            .onEach { uiResult ->
                when (uiResult) {
                    is UiResult.Success -> {
                        _state.value =
                            _state.value.copy(
                                result = CrudModel(resultCode = 200, message = SUCCESS),
                                isLoading = false
                            )
                    }

                    is UiResult.Error -> {
                        _state.value =
                            _state.value.copy(error = uiResult.message, isLoading = false)
                    }

                    is UiResult.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun checkOtp(otpList: MutableList<String>) {
        val otp = otpList.joinToString { "" }

        checkOtpUseCase.invoke(otp)
            .onEach { uiResult ->
                when (uiResult) {
                    is UiResult.Success -> {
                        _state.value =
                            _state.value.copy(
                                result = CrudModel(resultCode = 200, message = SUCCESS),
                                isLoading = false
                            )
                    }

                    is UiResult.Error -> {
                        _state.value =
                            _state.value.copy(error = uiResult.message, isLoading = false)
                    }

                    is UiResult.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun setNewPassword(password: String) {
        setNewPasswordUseCase.invoke(password)
            .onEach { uiResult ->
                when (uiResult) {
                    is UiResult.Success -> {
                        _state.value =
                            _state.value.copy(
                                result = CrudModel(resultCode = 200, message = SUCCESS),
                                isLoading = false
                            )
                    }

                    is UiResult.Error -> {
                        _state.value =
                            _state.value.copy(error = uiResult.message, isLoading = false)
                    }

                    is UiResult.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }


    companion object {
        private const val GMAIL_TAIL = "@gmail.com"
        private const val SUCCESS = "Success"
        private const val WRONG_FILLED_INPUT = "Fields are filled wrong"
    }
}