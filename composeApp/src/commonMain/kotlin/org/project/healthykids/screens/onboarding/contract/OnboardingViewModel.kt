package org.project.healthykids.screens.onboarding.contract

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natighajiyev.domain.model.CrudModel
import com.natighajiyev.domain.model.UiResult
import com.natighajiyev.domain.usecases.registration.GetUserInfoUseCase
import com.natighajiyev.domain.usecases.registration.HasUserViewedWalkthroughUseCase
import com.natighajiyev.domain.usecases.registration.UserViewedWalkthroughUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class OnboardingViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val hasUserViewedWalkthroughUseCase: HasUserViewedWalkthroughUseCase,
    private val userViewedWalkthroughUseCase: UserViewedWalkthroughUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(OnboardingContract.State())
    val state: State<OnboardingContract.State> = _state

    fun onEvent(intent: OnboardingContract.Intent) {
        when (intent) {
            is OnboardingContract.Intent.WalkthroughViewed -> markWalkthroughViewed()
            is OnboardingContract.Intent.HasUserViewed -> hasUserViewed()
            is OnboardingContract.Intent.GetUser -> getUser()
        }
    }

    private fun markWalkthroughViewed() {
        userViewedWalkthroughUseCase.invoke().onEach { uiResult ->
            when (uiResult) {
                is UiResult.Success -> {
                    _state.value =
                        _state.value.copy(
                            result = CrudModel(resultCode = 200, message = "Success"),
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

    private fun getUser() {
        getUserInfoUseCase.invoke().onEach { uiResult ->
            when (uiResult) {
                is UiResult.Success -> {
                    _state.value =
                        _state.value.copy(userInfo = uiResult.data, isLoading = false)
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

    private fun hasUserViewed() {
        hasUserViewedWalkthroughUseCase.invoke().onEach { uiResult ->
            _state.value = _state.value.copy(isViewed = uiResult)
        }.launchIn(viewModelScope)
    }
}