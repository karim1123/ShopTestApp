package karim.gabbasov.login

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import karim.gabbasov.feature_api.features.CatalogFeatureApi
import karim.gabbasov.login.core.LoginResult
import karim.gabbasov.login.core.LoginUseCase
import karim.gabbasov.login.ui.LoginVewModel
import karim.gabbasov.login.ui.LoginViewAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginVewModelTest {

    private val useCase = mockk<LoginUseCase>()
    private lateinit var viewModel: LoginVewModel
    private val catalogApi = mockk<CatalogFeatureApi>()

    @Before
    fun setup() {
        clearAllMocks()
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = LoginVewModel(
            loginUseCase = useCase,
            catalogFeatureApi = catalogApi
        )
    }

    @Test
    fun `When FirstNameChanged, Then updated username is emitted`() {
        val expected = "newFirstName"
        assertEquals("", viewModel.uiState.value.firstName)

        viewModel.execute(LoginViewAction.FirstNameChanged(expected))

        assertEquals(expected, viewModel.uiState.value.firstName)
    }

    @Test
    fun `When PasswordChanged, Then updated password is emitted`() {
        val expected = "newPassword"
        assertEquals("", viewModel.uiState.value.password)

        viewModel.execute(LoginViewAction.PasswordChanged(expected))

        assertEquals(expected, viewModel.uiState.value.password)
    }

    @Test
    fun `UserNotFound, When LoginPressed, Then error is emitted`() =
        runTest {
            coEvery {
                useCase(any(), any())
            } returns LoginResult.UserNotFound

            viewModel.apply {
                execute(LoginViewAction.FirstNameChanged("New FirstName"))
                execute(LoginViewAction.PasswordChanged("New Password"))
                execute(LoginViewAction.LoginPressed)
                delay(1000)
            }

            assertEquals(
                R.string.user_not_found_error,
                viewModel.uiState.value.errorMessage
            )
        }

    @Test
    fun `When ErrorSnackbarObserved, Then new UIState is emitted`() {
        runTest {
            coEvery {
                useCase(any(), any())
            } returns LoginResult.UserNotFound

            viewModel.apply {
                execute(LoginViewAction.FirstNameChanged("New FirstName"))
                execute(LoginViewAction.PasswordChanged("New Password"))
                execute(LoginViewAction.LoginPressed)
                delay(1000)
            }

            assertEquals(
                R.string.user_not_found_error,
                viewModel.uiState.value.errorMessage
            )
            viewModel.execute(LoginViewAction.ErrorObserved)

            Assert.assertNull(viewModel.uiState.value.errorMessage)
        }
    }

    @Test
    fun `Success, When LoginPressed, Then navigation event is emitted`() {
        runTest {
            coEvery {
                useCase(any(), any())
            } returns LoginResult.Success

            viewModel.apply {
                execute(LoginViewAction.FirstNameChanged("New FirstName"))
                execute(LoginViewAction.PasswordChanged("New Password"))
                execute(LoginViewAction.LoginPressed)
                delay(1000)
            }

            assertEquals(true, viewModel.uiState.value.navigateScreenEvent)
        }
    }

    @Test
    fun `When NavigationObserved, Then new UIState is emitted`() {
        runTest {
            coEvery {
                useCase(any(), any())
            } returns LoginResult.Success

            viewModel.apply {
                execute(LoginViewAction.FirstNameChanged("New FirstName"))
                execute(LoginViewAction.PasswordChanged("New Password"))
                execute(LoginViewAction.LoginPressed)
                delay(1000)
            }

            assertEquals(true, viewModel.uiState.value.navigateScreenEvent)
            viewModel.execute(LoginViewAction.NavigationObserved)

            Assert.assertNull(viewModel.uiState.value.navigateScreenEvent)
        }
    }
}
