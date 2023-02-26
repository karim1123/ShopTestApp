package karim.gabbasov.sign_in

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import karim.gabbasov.data.repository.user.UserApiResponse
import karim.gabbasov.data.repository.user.UserRepository
import karim.gabbasov.sign_in.core.SignInResult
import karim.gabbasov.sign_in.core.SignInUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SignInUseCaseTest {

    private val repository = mockk<UserRepository>()
    private lateinit var useCase: SignInUseCase

    @Before
    fun setup() {
        clearAllMocks()
        useCase = SignInUseCase(repository)
    }

    @Test
    fun `Given firstName is invalid, Then FirstNameInvalid error returned`() = runTest {
        val result = useCase("", "lastName", "email")

        assertEquals(SignInResult.FirstNameInvalid, result)
    }

    @Test
    fun `Given lastName is invalid, Then astNameInvalid error returned`() = runTest {
        val result = useCase("firstName", "", "email")

        assertEquals(SignInResult.LastNameInvalid, result)
    }

    @Test
    fun `Given email is invalid, Then astNameInvalid error returned`() = runTest {
        val result = useCase("firstName", "lastName", "email")

        assertEquals(SignInResult.EmailInvalid, result)
    }

    @Test
    fun `User exist, Then UserAlreadyExist error returned`() = runTest {
        coEvery { repository.insertUser(any()) } returns UserApiResponse.UserAlreadyExist

        val result = useCase("firstName", "password", "test@email.com")

        assertEquals(SignInResult.UserAlreadyExist, result)
    }

    @Test
    fun `Given successful sign in, Then Success is returned`() = runTest {
        coEvery { repository.insertUser(any()) } returns UserApiResponse.Success

        val result = useCase("firstName", "lastname", "test@email.com")

        assertEquals(SignInResult.Success, result)
    }
}
