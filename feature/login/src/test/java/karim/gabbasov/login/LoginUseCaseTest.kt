package karim.gabbasov.login

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import karim.gabbasov.data.repository.user.UserRepository
import karim.gabbasov.login.core.LoginResult
import karim.gabbasov.login.core.LoginUseCase
import karim.gabbasov.model.data.UserDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginUseCaseTest {

    private val repository = mockk<UserRepository>()
    private lateinit var useCase: LoginUseCase

    @Before
    fun setup() {
        clearAllMocks()
        useCase = LoginUseCase(repository)
    }

    @Test
    fun `Given firstName is invalid, Then FirstNameInvalid error returned`() = runTest {
        val result = useCase("", "password")

        assertEquals(LoginResult.FirstNameInvalid, result)
    }

    @Test
    fun `Given password is invalid, Then PasswordInvalid error returned`() = runTest {
        val result = useCase("firstName", "")

        assertEquals(LoginResult.PasswordInvalid, result)
    }

    @Test
    fun `User not exist, Then UserNotFound error returned`() = runTest {
        coEvery { repository.getUser(any()) } returns null

        val result = useCase("firstName", "password")

        assertEquals(LoginResult.UserNotFound, result)
    }

    @Test
    fun `Given successful login, Then Success is returned`() = runTest {
        val user = UserDomain(
            email = "email",
            firstName = "firstName",
            lastName = "lastName"
        )
        coEvery { repository.getUser(any()) } returns user

        val result = useCase("firstName", "password")

        assertEquals(LoginResult.Success, result)
    }
}
