package org.project.healthykids.screens.main.profile.contract

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natighajiyev.domain.model.CrudModel
import com.natighajiyev.domain.model.UiResult
import com.natighajiyev.domain.usecases.profile.GetAllergiesNameUseCase
import com.natighajiyev.domain.usecases.profile.GetLastSentFilesNameUseCase
import com.natighajiyev.domain.usecases.profile.GetMyChildrenListUseCase
import com.natighajiyev.domain.usecases.profile.GetVaccinesNameUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.project.healthykids.common.SUCCESS

class ProfileViewModel(
    private val getMyChildrenListUseCase: GetMyChildrenListUseCase,
    private val getAllergiesNameUseCase: GetAllergiesNameUseCase,
    private val getVaccinesNameUseCase: GetVaccinesNameUseCase,
    private val getLastSentFilesNameUseCase: GetLastSentFilesNameUseCase
): ViewModel() {

    private val _state: MutableState<ProfileContract.State> = mutableStateOf(ProfileContract.State())
    val state: State<ProfileContract.State> = _state

    private val _effect: Channel<ProfileContract.Effect> = Channel<ProfileContract.Effect>()
    val effect: Flow<ProfileContract.Effect> = _effect.receiveAsFlow()

    fun onEvent(intent: ProfileContract.Intent) {
        when (intent) {
            is ProfileContract.Intent.GetChildrenList -> getMyChildrenList()
            is ProfileContract.Intent.GetVaccinesList -> getVaccines()
            is ProfileContract.Intent.GetAllergiesList -> getAllergies()
            is ProfileContract.Intent.GetLastAddedFilesList -> getLastAddedFiles()
        }
    }

    private fun postEffect(effect: ProfileContract.Effect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }

    private fun getMyChildrenList() {
        getMyChildrenListUseCase.invoke().onEach { uiResult ->
            when (uiResult) {
                is UiResult.Success -> {
                    _state.value =
                        _state.value.copy(
                            result = CrudModel(resultCode = 200, message = SUCCESS),
                            isLoading = false,
                            childrenList = uiResult.data
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

    private fun getVaccines() {
        getVaccinesNameUseCase.invoke().onEach { uiResult ->
            when (uiResult) {
                is UiResult.Success -> {
                    _state.value =
                        _state.value.copy(
                            result = CrudModel(resultCode = 200, message = SUCCESS),
                            isLoading = false,
                            vaccinesList = uiResult.data
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

    private fun getAllergies() {
        getAllergiesNameUseCase.invoke().onEach { uiResult ->
            when (uiResult) {
                is UiResult.Success -> {
                    _state.value =
                        _state.value.copy(
                            result = CrudModel(resultCode = 200, message = SUCCESS),
                            isLoading = false,
                            allergiesList = uiResult.data
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

    private fun getLastAddedFiles() {
        getLastSentFilesNameUseCase.invoke().onEach { uiResult ->
            when (uiResult) {
                is UiResult.Success -> {
                    _state.value =
                        _state.value.copy(
                            result = CrudModel(resultCode = 200, message = SUCCESS),
                            isLoading = false,
                            lastAddedFilesName = uiResult.data
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