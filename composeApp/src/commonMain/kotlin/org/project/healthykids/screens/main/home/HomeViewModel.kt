package org.project.healthykids.screens.main.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natighajiyev.domain.model.CrudModel
import com.natighajiyev.domain.model.UiResult
import com.natighajiyev.domain.usecases.home.GetPersonalInfoUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.project.healthykids.common.SUCCESS

class HomeViewModel(
    private val getPersonalInfoUseCase: GetPersonalInfoUseCase,
) : ViewModel() {

    private val _state: MutableState<HomeContract.State> = mutableStateOf(HomeContract.State())
    val state: State<HomeContract.State> = _state

    private val _effect: Channel<HomeContract.Effect> = Channel<HomeContract.Effect>()
    val effect: Flow<HomeContract.Effect> = _effect.receiveAsFlow()

    fun onEvent(intent: HomeContract.Intent) {
        when (intent) {
            is HomeContract.Intent.GetPersonalInfo -> getPersonalInfo()
        }
    }

    private fun postEffect(effect: HomeContract.Effect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }

    private fun getPersonalInfo() {
        getPersonalInfoUseCase.invoke().onEach { uiResult ->
            when (uiResult) {
                is UiResult.Success -> {
                    _state.value =
                        _state.value.copy(
                            result = CrudModel(resultCode = 200, message = SUCCESS),
                            isLoading = false,
                            personalInfo = uiResult.data
                        )
                }

                is UiResult.Error -> {
                    _state.value =
                        _state.value.copy(
                            error = uiResult.message,
                            isLoading = false,
                            result = CrudModel(resultCode = uiResult.responseCode, message = uiResult.message)
                        )
                }

                is UiResult.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}