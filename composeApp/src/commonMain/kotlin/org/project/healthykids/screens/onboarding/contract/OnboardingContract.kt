package org.project.healthykids.screens.contract

import com.natighajiyev.domain.model.CrudModel
import org.project.healthykids.models.WalkthroughModel

object OnboardingContract {

    sealed interface Intent {
        data object WalkthroughViewed: Intent
        data object GetUser: Intent
    }

    sealed interface Effect {

    }

    data class State(
        var isLoading: Boolean = false,
        var error: String? = null,
        var isViewed: Boolean = false,
        var list: List<WalkthroughModel>? = null,
        var result: CrudModel? = null
    )
}
