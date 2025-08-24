package org.project.healthykids.screens.main.profile.contract

import com.natighajiyev.domain.model.CrudModel
import com.natighajiyev.domain.network.response.ChildrenSelectionListResponse
import com.natighajiyev.domain.network.response.ListAllergyResponse
import com.natighajiyev.domain.network.response.ListFileNameResponse

object ProfileContract {

    sealed interface Intent{
        data object GetChildrenList : Intent
        data object GetVaccinesList : Intent
        data object GetAllergiesList : Intent
        data object GetLastAddedFilesList : Intent
    }

    sealed interface Effect {
        data class Navigate(val route: String) : Effect
    }

    data class State(
        var isLoading: Boolean = false,
        var error: String? = null,
        var result: CrudModel? = null,
        var childrenList: ChildrenSelectionListResponse? = null,
        var vaccinesList: List<String>? = null,
        var allergiesList: ListAllergyResponse? = null,
        var lastAddedFilesName: ListFileNameResponse? = null
    )

}