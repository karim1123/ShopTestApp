package karim.gabbasov.login.core

import karim.gabbasov.data.repository.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    private fun isInputInvalid(input: String): Boolean = input.isBlank()

    suspend operator fun invoke(
        firstName: String,
        password: String
    ): LoginResult {
        return if (isInputInvalid(firstName)) {
            LoginResult.FirstNameInvalid
        } else if (isInputInvalid(password)) {
            return LoginResult.PasswordInvalid
        } else {
            return login(
                firstName
            )
        }
    }

    private suspend fun login(firstName: String): LoginResult = withContext(Dispatchers.IO) {
        val response = userRepository.authorize(firstName)
        if (response) return@withContext LoginResult.Success
        else return@withContext LoginResult.UserNotFound
    }
}

internal sealed class LoginResult {
    object Success : LoginResult()
    object FirstNameInvalid : LoginResult()
    object PasswordInvalid : LoginResult()
    object UserNotFound : LoginResult()
}
