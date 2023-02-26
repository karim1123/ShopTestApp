package karim.gabbasov.sign_in.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import karim.gabbasov.feature_api.features.LoginFeatureApi
import karim.gabbasov.sign_in.R
import karim.gabbasov.sign_in.core.SignInResult
import karim.gabbasov.sign_in.core.SignInUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    val loginFeatureApi: LoginFeatureApi
) : ViewModel() {
    private val _uiState = MutableStateFlow(UIState.empty)
    val uiState = _uiState.asStateFlow()
    private var state: UIState
        get() = _uiState.value
        set(value) {
            _uiState.update { value }
        }

    internal fun execute(action: SignInViewAction) {
        when (action) {
            is SignInViewAction.FirstNameChanged -> state = state.copy(
                firstName = action.newFirstName,
                hasFirstNameError = false,
                signInButtonEnabled = action.newFirstName.isNotBlank() &&
                    state.lastName.isNotBlank() && state.email.isNotBlank()
            )

            is SignInViewAction.LastNameChanged -> state = state.copy(
                lastName = action.newLastName,
                hasLastNameError = false,
                signInButtonEnabled = state.firstName.isNotBlank() &&
                    action.newLastName.isNotBlank() && state.email.isNotBlank()
            )
            is SignInViewAction.EmailChanged -> state = state.copy(
                email = action.newEmail,
                hasEmailError = false,
                signInButtonEnabled = state.firstName.isNotBlank() &&
                    state.lastName.isNotBlank() && action.newEmail.isNotBlank()
            )
            SignInViewAction.SignInPressed -> signIn()
            SignInViewAction.ErrorObserved ->
                state =
                    state.copy(errorMessage = null)
            SignInViewAction.NavigationObserved -> state = state.copy(navigateScreenEvent = null)
        }
    }

    private fun signIn() = viewModelScope.launch {
        state = state.copy(isPerformingSignIn = true)
        delay(1000)

        val response = signInUseCase(
            firstName = state.firstName,
            lastName = state.lastName,
            email = state.email
        )
        when (response) {
            SignInResult.Success -> navigateToNextScreen()
            else -> handleSignInError(response)
        }
    }

    private fun navigateToNextScreen() {
        state = state.copy(
            isPerformingSignIn = false,
            navigateScreenEvent = true
        )
    }

    private fun handleSignInError(error: SignInResult) {
        val newState = when (error) {
            SignInResult.FirstNameInvalid -> state.copy(
                hasFirstNameError = true,
                isPerformingSignIn = false
            )
            SignInResult.LastNameInvalid -> state.copy(
                hasLastNameError = true,
                isPerformingSignIn = false
            )
            SignInResult.EmailInvalid -> state.copy(
                hasEmailError = true,
                isPerformingSignIn = false,
                errorMessage = R.string.invalid_email_error
            )
            SignInResult.UserAlreadyExist -> state.copy(
                isPerformingSignIn = false,
                errorMessage = R.string.user_exist_error
            )
            else -> TODO()
        }
        state = newState
    }
}
