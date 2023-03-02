package karim.gabbasov.sign_in.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import karim.gabbasov.sign_in.R
import karim.gabbasov.ui.theme.AccountQuestion
import karim.gabbasov.ui.theme.Black
import karim.gabbasov.ui.theme.LoginLink
import karim.gabbasov.ui.theme.OnlineShopTheme
import karim.gabbasov.ui.ui.AuthButton
import karim.gabbasov.ui.ui.TextFieldInput
import karim.gabbasov.ui.ui.Title
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
internal fun SignInScreenRoute(
    navController: NavHostController,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val navigate = state.value.navigateScreenEvent
    if (navigate == true) {
        LaunchedEffect(navigate) {
            navController.navigate(viewModel.catalogFeatureApi.catalogRoute()) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }
    }

    OnlineShopTheme {
        SignInScreen(
            state = state,
            onFirstNameChanged = { viewModel.execute(SignInViewAction.FirstNameChanged(it)) },
            onLastNameChanged = { viewModel.execute(SignInViewAction.LastNameChanged(it)) },
            onEmailChanged = { viewModel.execute(SignInViewAction.EmailChanged(it)) },
            onSignInPressed = { viewModel.execute(SignInViewAction.SignInPressed) },
            onErrorShown = { viewModel.execute(SignInViewAction.ErrorObserved) },
            onLoginPressed = {
                val loginFeatureApi = viewModel.loginFeatureApi
                navController.navigate(loginFeatureApi.loginRoute())
            }
        )
    }
}

@Composable
private fun SignInScreen(
    state: State<UIState>,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onSignInPressed: () -> Unit,
    onLoginPressed: () -> Unit,
    onErrorShown: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(OnlineShopTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(150.dp))
        Title(title = stringResource(R.string.sign_in_title))
        Spacer(modifier = Modifier.height(60.dp))
        val focusManager = LocalFocusManager.current
        TextFieldInput(
            modifier = Modifier
                .padding(bottom = 35.dp, start = 45.dp, end = 45.dp)
                .fillMaxWidth(),
            text = state.value.firstName,
            isTextValueError = state.value.hasFirstNameError,
            onTextValueChanged = onFirstNameChanged,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            placeholder = stringResource(R.string.first_name_placeholder)
        )
        TextFieldInput(
            modifier = Modifier
                .padding(bottom = 35.dp, start = 45.dp, end = 45.dp)
                .fillMaxWidth(),
            text = state.value.lastName,
            isTextValueError = state.value.hasLastNameError,
            onTextValueChanged = onLastNameChanged,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            placeholder = stringResource(R.string.last_name_placeholder)
        )
        TextFieldInput(
            modifier = Modifier
                .padding(bottom = 35.dp, start = 45.dp, end = 45.dp)
                .fillMaxWidth(),
            text = state.value.email,
            isTextValueError = state.value.hasEmailError,
            onTextValueChanged = onEmailChanged,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            placeholder = stringResource(R.string.email_placeholder)
        )

        if (state.value.isPerformingSignIn) {
            CircularProgressIndicator()
        } else {
            AuthButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp, start = 45.dp, end = 45.dp),
                onLoginPressed = onSignInPressed,
                text = stringResource(R.string.sign_in_title),
                isEnabled = state.value.signInButtonEnabled,
                isExecuting = state.value.isPerformingSignIn
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .padding(start = 45.dp)
        ) {
            Text(
                modifier = Modifier.padding(end = 6.dp),
                text = stringResource(R.string.have_account_question),
                color = AccountQuestion,
                style = OnlineShopTheme.typography.bodySmall
            )
            Text(
                modifier = Modifier.clickable(
                    role = Role.Button
                ) { onLoginPressed.invoke() },
                text = stringResource(R.string.log_in_button_text),
                color = LoginLink,
                style = OnlineShopTheme.typography.bodySmall
            )
        }
        Column {
            SignInButton(
                modifier = Modifier.padding(top = 70.dp),
                imageVector = ImageVector.vectorResource(R.drawable.google),
                text = stringResource(R.string.sigh_in_with_google_button_text)
            )

            SignInButton(
                modifier = Modifier.padding(top = 20.dp),
                imageVector = ImageVector.vectorResource(R.drawable.apple),
                text = stringResource(R.string.sigh_in_with_apple_button_text)
            )
        }
    }

    val context = LocalContext.current
    val errorMessage = state.value.errorMessage
    if (errorMessage != null) {
        val message = stringResource(id = errorMessage)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        onErrorShown()
    }
}

@Composable
private fun SignInButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    text: String
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        onClick = { /*TODO*/ }
    ) {
        Row(
            modifier = Modifier.wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 6.dp),
                imageVector = imageVector,
                contentDescription = text
            )
            Text(
                text = text,
                color = Black,
                style = OnlineShopTheme.typography.labelMedium
            )
        }
    }
}

@Preview
@Composable
fun PreviewSignInScreen() {
    val state = MutableStateFlow(UIState.empty).collectAsState()

    SignInScreen(
        state = state,
        onFirstNameChanged = { },
        onLastNameChanged = { },
        onEmailChanged = {},
        onSignInPressed = { },
        onErrorShown = { },
        onLoginPressed = { }
    )
}

@Preview
@Composable
fun PreviewSignInButton() {
    SignInButton(
        imageVector = ImageVector.vectorResource(R.drawable.google),
        text = "Sign in with Google"
    )
}
