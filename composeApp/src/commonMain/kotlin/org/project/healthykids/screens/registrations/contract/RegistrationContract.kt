package org.project.healthykids.screens.registrations.contract

import com.natighajiyev.domain.model.CrudModel

object RegistrationContract {

    sealed interface Intent {
        data class Login(
            val email: String,
            val password: String
        ): Intent
        data class CreateAccount(
            val email: String,
            val password: String,
            val fullname: String,
            val phone: String
        ): Intent
        data class ResetPassword(val email: String): Intent
        data class CheckOtp(val otp: MutableList<String>): Intent
        data class SetNewPassword(val newPassword: String): Intent
    }

    sealed interface Effect {
        data class WrongInputFormat(val msg: String): Effect
        data object Navigate: Effect
    }

    data class State(
        var isLoading: Boolean = false,
        var error: String? = null,
        var result: CrudModel? = null,

    )
}