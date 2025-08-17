package org.project.healthykids.screens.contract

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class OnboardingViewModel(

): ViewModel() {

    private val _state = mutableStateOf(OnboardingContract.State())
    val state: State<OnboardingContract.State> = _state

    fun onEvent(intent: OnboardingContract.Intent) {
        when(intent) {
            is OnboardingContract.Intent.WalkthroughViewed -> markWalkthroughViewed()
            is OnboardingContract.Intent.GetUser -> getUser()
        }
    }

    private fun markWalkthroughViewed() {
        // TODO: Local db config
    }

    private fun getUser() {
        // TODO: Network call for getting user
    }
}