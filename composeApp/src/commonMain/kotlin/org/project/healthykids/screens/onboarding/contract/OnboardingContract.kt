package org.project.healthykids.screens.onboarding.contract

import com.natighajiyev.domain.model.CrudModel
import com.natighajiyev.domain.network.response.AccountInfoResponse
import healthykids.composeapp.generated.resources.Res
import healthykids.composeapp.generated.resources.ic_walkthrough_1
import healthykids.composeapp.generated.resources.ic_walkthrough_2
import healthykids.composeapp.generated.resources.ic_walkthrough_3
import org.project.healthykids.models.WalkthroughModel

object OnboardingContract {

    sealed interface Intent {
        data object WalkthroughViewed: Intent
        data object HasUserViewed: Intent
        data object GetUser: Intent
    }

    sealed interface Effect {

    }

    data class State(
        var isLoading: Boolean = false,
        var error: String? = null,
        var isViewed: Boolean = false,
        var userInfo: AccountInfoResponse? = null,
        var list: List<WalkthroughModel> =
            listOf(
                WalkthroughModel(
                    imageId = Res.drawable.ic_walkthrough_1,
                    title = "Забота о спине - легко и весело",
                    desc = "Игры, советы и упражнения для здоровья вашей спины"
                ),
                WalkthroughModel(
                    imageId = Res.drawable.ic_walkthrough_2,
                    title = "Забота о спине - легко и весело",
                    desc = "Игры, советы и упражнения для здоровья вашей спины"
                ),
                WalkthroughModel(
                    imageId = Res.drawable.ic_walkthrough_3,
                    title = "Забота о спине - легко и весело",
                    desc = "Игры, советы и упражнения для здоровья вашей спины"
                )
            ),
        var result: CrudModel? = null
    )
}
