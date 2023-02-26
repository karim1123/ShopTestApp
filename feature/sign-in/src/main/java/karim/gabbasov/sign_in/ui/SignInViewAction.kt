package karim.gabbasov.sign_in.ui

internal sealed interface SignInViewAction {
    data class FirstNameChanged(val newFirstName: String) : SignInViewAction
    data class LastNameChanged(val newLastName: String) : SignInViewAction
    data class EmailChanged(val newEmail: String) : SignInViewAction
    object SignInPressed : SignInViewAction
    object ErrorObserved : SignInViewAction
    object NavigationObserved : SignInViewAction
}
