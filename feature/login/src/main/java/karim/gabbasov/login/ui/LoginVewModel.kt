package karim.gabbasov.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import karim.gabbasov.feature_api.features.CatalogFeatureApi
import karim.gabbasov.login.R
import karim.gabbasov.login.core.LoginResult
import karim.gabbasov.login.core.LoginUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginVewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    val catalogFeatureApi: CatalogFeatureApi
) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState.empty)
    val uiState = _uiState.asStateFlow()
    private var state: UIState
        get() = _uiState.value
        set(value) {
            _uiState.update { value }
        }

    fun execute(viewAction: LoginViewAction) {
        when (viewAction) {
            is LoginViewAction.FirstNameChanged -> state = state.copy(
                firstName = viewAction.newFirstName,
                hasFirstNameError = false,
                loginButtonEnabled =
                viewAction.newFirstName.isNotBlank() && state.password.isNotBlank()
            )
            is LoginViewAction.PasswordChanged -> state = state.copy(
                password = viewAction.newPassword,
                hasPasswordError = false,
                loginButtonEnabled =
                state.firstName.isNotBlank() && viewAction.newPassword.isNotBlank()
            )
            LoginViewAction.LoginPressed -> login()
            LoginViewAction.ErrorObserved -> state = state.copy(errorMessage = null)
            LoginViewAction.NavigationObserved -> state = state.copy(navigateScreenEvent = null)
        }
    }

    private fun login() = viewModelScope.launch {
        state = state.copy(isPerformingLogin = true)
        delay(1000)

        val response = loginUseCase.invoke(
            firstName = state.firstName,
            password = state.password
        )
        when (response) {
            LoginResult.Success -> navigateToNextScreen()
            else -> handleLoginError(response)
        }
    }

    private fun navigateToNextScreen() {
        state = state.copy(
            isPerformingLogin = false,
            navigateScreenEvent = true
        )
    }

    private fun handleLoginError(error: LoginResult) {
        val newState = when (error) {
            LoginResult.FirstNameInvalid -> state.copy(
                hasFirstNameError = true,
                isPerformingLogin = false
            )
            LoginResult.PasswordInvalid -> state.copy(
                hasPasswordError = true,
                isPerformingLogin = false
            )
            LoginResult.UserNotFound -> state.copy(
                isPerformingLogin = false,
                errorMessage = R.string.user_not_found_error
            )
            else -> TODO()
        }
        state = newState
    }
}
