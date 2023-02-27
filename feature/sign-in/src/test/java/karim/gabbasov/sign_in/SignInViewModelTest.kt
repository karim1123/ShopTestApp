package karim.gabbasov.sign_in

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import karim.gabbasov.data.repository.user.UserRepository
import karim.gabbasov.feature_api.features.CatalogFeatureApi
import karim.gabbasov.feature_api.features.LoginFeatureApi
import karim.gabbasov.sign_in.core.SignInResult
import karim.gabbasov.sign_in.core.SignInUseCase
import karim.gabbasov.sign_in.ui.SignInViewAction
import karim.gabbasov.sign_in.ui.SignInViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SignInViewModelTest {

    private val useCase = mockk<SignInUseCase>()
    private val loginApi = mockk<LoginFeatureApi>()
    private val catalogApi = mockk<CatalogFeatureApi>()
    private lateinit var viewModel: SignInViewModel

    @Before
    fun setup() {
        clearAllMocks()
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = SignInViewModel(
            signInUseCase = useCase,
            loginFeatureApi = loginApi,
            catalogFeatureApi = catalogApi
        )
    }

    @Test
    fun `When FirstNameChanged, Then updated username is emitted`() {
        val expected = "newFirstName"
        Assert.assertEquals("", viewModel.uiState.value.firstName)

        viewModel.execute(SignInViewAction.FirstNameChanged(expected))

        Assert.assertEquals(expected, viewModel.uiState.value.firstName)
    }

    @Test
    fun `When LastNameChanged, Then updated lastname is emitted`() {
        val expected = "newLastName"
        Assert.assertEquals("", viewModel.uiState.value.lastName)

        viewModel.execute(SignInViewAction.LastNameChanged(expected))

        Assert.assertEquals(expected, viewModel.uiState.value.lastName)
    }

    @Test
    fun `When EmailChanged, Then updated email is emitted`() {
        val expected = "newEmail"
        Assert.assertEquals("", viewModel.uiState.value.email)

        viewModel.execute(SignInViewAction.EmailChanged(expected))

        Assert.assertEquals(expected, viewModel.uiState.value.email)
    }

    @Test
    fun `UserAlreadyExist, When SignInPressed, Then error is emitted`() =
        runTest {
            coEvery {
                useCase(any(), any(), any())
            } returns SignInResult.UserAlreadyExist

            viewModel.apply {
                execute(SignInViewAction.FirstNameChanged("New FirstName"))
                execute(SignInViewAction.LastNameChanged("New LastName"))
                execute(SignInViewAction.EmailChanged("newtest@mail.com"))
                execute(SignInViewAction.SignInPressed)
                delay(1000)
            }

            Assert.assertEquals(
                R.string.user_exist_error,
                viewModel.uiState.value.errorMessage
            )
        }

    @Test
    fun `Invalid email, When SignInPressed, Then error is emitted`() =
        runTest {
            val repository = mockk<UserRepository>()
            val useCase = SignInUseCase(repository)
            val loginApi = mockk<LoginFeatureApi>()
            val viewModel = SignInViewModel(
                signInUseCase = useCase,
                loginFeatureApi = loginApi,
                catalogFeatureApi = catalogApi
            )

            viewModel.apply {
                execute(SignInViewAction.FirstNameChanged("New FirstName"))
                execute(SignInViewAction.LastNameChanged("New LastName"))
                execute(SignInViewAction.EmailChanged("newTestMail"))
                execute(SignInViewAction.SignInPressed)
                delay(1000)
            }

            Assert.assertEquals(
                R.string.invalid_email_error,
                viewModel.uiState.value.errorMessage
            )
        }

    @Test
    fun `When ErrorObserved, Then new UIState is emitted`() {
        runTest {
            coEvery {
                useCase(any(), any(), any())
            } returns SignInResult.UserAlreadyExist

            viewModel.apply {
                execute(SignInViewAction.FirstNameChanged("New FirstName"))
                execute(SignInViewAction.LastNameChanged("New LastName"))
                execute(SignInViewAction.EmailChanged("newtest@mail.com"))
                execute(SignInViewAction.SignInPressed)
                delay(1000)
            }

            Assert.assertEquals(
                R.string.user_exist_error,
                viewModel.uiState.value.errorMessage
            )
            viewModel.execute(SignInViewAction.ErrorObserved)

            Assert.assertNull(viewModel.uiState.value.errorMessage)
        }
    }

    @Test
    fun `Success, When LoginPressed, Then navigation event is emitted`() {
        runTest {
            coEvery {
                useCase(any(), any(), any())
            } returns SignInResult.Success

            viewModel.apply {
                execute(SignInViewAction.FirstNameChanged("New FirstName"))
                execute(SignInViewAction.LastNameChanged("New LastName"))
                execute(SignInViewAction.EmailChanged("newtest@mail.com"))
                execute(SignInViewAction.SignInPressed)
                delay(1000)
            }

            Assert.assertEquals(true, viewModel.uiState.value.navigateScreenEvent)
        }
    }

    @Test
    fun `When NavigationObserved, Then new UIState is emitted`() {
        runTest {
            coEvery {
                useCase(any(), any(), any())
            } returns SignInResult.Success

            viewModel.apply {
                execute(SignInViewAction.FirstNameChanged("New FirstName"))
                execute(SignInViewAction.LastNameChanged("New LastName"))
                execute(SignInViewAction.EmailChanged("newtest@mail.com"))
                execute(SignInViewAction.SignInPressed)
                delay(1000)
            }

            Assert.assertEquals(true, viewModel.uiState.value.navigateScreenEvent)
            viewModel.execute(SignInViewAction.NavigationObserved)

            Assert.assertNull(viewModel.uiState.value.navigateScreenEvent)
        }
    }
}
