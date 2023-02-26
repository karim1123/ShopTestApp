package karim.gabbasov.sign_in.core

import karim.gabbasov.data.repository.user.UserApiResponse
import karim.gabbasov.data.repository.user.UserRepository
import karim.gabbasov.model.data.UserDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val REGEX_PATTERN = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b"

internal class SignInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    private fun isPartOfNameInvalid(partOfName: String): Boolean = partOfName.isBlank()

    private fun isEmailInvalid(email: String): Boolean {
        val emailRegex = Regex(REGEX_PATTERN)
        return email.isBlank() || !emailRegex.matches(email)
    }

    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        email: String
    ): SignInResult {
        return if (isPartOfNameInvalid(firstName)) {
            SignInResult.FirstNameInvalid
        } else if (isPartOfNameInvalid(lastName)) {
            return SignInResult.LastNameInvalid
        } else if (isEmailInvalid(email)) {
            return SignInResult.EmailInvalid
        } else {
            return signIn(
                UserDomain(
                    firstName = firstName,
                    lastName = lastName,
                    email = email
                )
            )
        }
    }

    private suspend fun signIn(user: UserDomain): SignInResult = withContext(Dispatchers.IO) {
        return@withContext when (userRepository.insertUser(user)) {
            UserApiResponse.UserAlreadyExist -> SignInResult.UserAlreadyExist
            UserApiResponse.Success -> SignInResult.Success
        }
    }
}

internal sealed class SignInResult {
    object Success : SignInResult()
    object FirstNameInvalid : SignInResult()
    object LastNameInvalid : SignInResult()
    object EmailInvalid : SignInResult()
    object UserAlreadyExist : SignInResult()
}
