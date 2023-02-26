package karim.gabbasov.login.ui

internal sealed interface LoginViewAction {
    data class FirstNameChanged(val newFirstName: String) : LoginViewAction
    data class PasswordChanged(val newPassword: String) : LoginViewAction
    object LoginPressed : LoginViewAction
    object ErrorObserved : LoginViewAction
    object NavigationObserved : LoginViewAction
}
