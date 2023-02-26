package karim.gabbasov.sign_in.ui

import androidx.annotation.StringRes

internal data class UIState(
    val firstName: String,
    val hasFirstNameError: Boolean,
    val lastName: String,
    val hasLastNameError: Boolean,
    val email: String,
    val hasEmailError: Boolean,
    val isPerformingSignIn: Boolean,
    val signInButtonEnabled: Boolean,
    @StringRes val errorMessage: Int?,
    val navigateScreenEvent: Boolean?
) {
    companion object {
        val empty: UIState
            get() = UIState(
                firstName = "",
                hasFirstNameError = false,
                lastName = "",
                hasLastNameError = false,
                email = "",
                hasEmailError = false,
                isPerformingSignIn = false,
                signInButtonEnabled = false,
                errorMessage = null,
                navigateScreenEvent = null
            )
    }
}
