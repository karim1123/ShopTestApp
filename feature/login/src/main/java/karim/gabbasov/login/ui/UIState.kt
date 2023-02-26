package karim.gabbasov.login.ui

import androidx.annotation.StringRes

internal data class UIState(
    val firstName: String,
    val hasFirstNameError: Boolean,
    val password: String,
    val hasPasswordError: Boolean,
    val isPerformingLogin: Boolean,
    val loginButtonEnabled: Boolean,
    @StringRes val errorMessage: Int?,
    val navigateScreenEvent: Boolean?
) {
    companion object {
        val empty: UIState
            get() = UIState(
                firstName = "",
                hasFirstNameError = false,
                password = "",
                hasPasswordError = false,
                isPerformingLogin = false,
                loginButtonEnabled = false,
                errorMessage = null,
                navigateScreenEvent = null
            )
    }
}
