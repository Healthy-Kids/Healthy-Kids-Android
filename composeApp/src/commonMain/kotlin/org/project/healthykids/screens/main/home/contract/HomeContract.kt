package org.project.healthykids.screens.main.home.contract

import com.natighajiyev.domain.model.CrudModel
import com.natighajiyev.domain.network.response.PersonalInfoResponse

object HomeContract {

    sealed interface Intent {
        data object GetPersonalInfo: Intent
    }

    sealed interface Effect {
        data class Navigate(val route: String): Effect

    }

    data class State(
        var isLoading: Boolean = false,
        var error: String? = null,
        var result: CrudModel? = null,
        var personalInfo: PersonalInfoResponse? = null
    )
}