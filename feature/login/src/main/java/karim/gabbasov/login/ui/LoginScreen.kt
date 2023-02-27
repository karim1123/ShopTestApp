package karim.gabbasov.login.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import karim.gabbasov.login.R
import karim.gabbasov.ui.theme.Black
import karim.gabbasov.ui.theme.OnlineShopTheme
import karim.gabbasov.ui.ui.AuthButton
import karim.gabbasov.ui.ui.InputTextFieldPlaceholder
import karim.gabbasov.ui.ui.TextFieldInput
import karim.gabbasov.ui.ui.Title
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
internal fun LoginScreenRoute(
    navController: NavHostController,
    viewModel: LoginVewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()
    val navigate = state.value.navigateScreenEvent
    if (navigate == true) {
        LaunchedEffect(navigate) {
            val catalogFeatureApi = viewModel.catalogFeatureApi
            navController.popBackStack()
            navController.navigate(catalogFeatureApi.catalogRoute())
        }
    }
    LoginScreen(
        state = state,
        onFirstNameChanged = { viewModel.execute(LoginViewAction.FirstNameChanged(it)) },
        onPasswordChanged = { viewModel.execute(LoginViewAction.PasswordChanged(it)) },
        onLoginPressed = { viewModel.execute(LoginViewAction.LoginPressed) },
        onErrorShown = { viewModel.execute(LoginViewAction.ErrorObserved) }
    )
}

@Composable
private fun LoginScreen(
    state: State<UIState>,
    onFirstNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
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
        Title(title = stringResource(R.string.login_title))
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
        PasswordInput(
            modifier = Modifier
                .padding(start = 45.dp, end = 45.dp)
                .fillMaxWidth(),
            password = state.value.password,
            isPasswordError = state.value.hasPasswordError,
            onPasswordChanged = onPasswordChanged,
            onLoginPressed = onLoginPressed,
            isLoggingIn = state.value.isPerformingLogin,
            placeholder = stringResource(R.string.password_placeholder)
        )
        Spacer(modifier = Modifier.height(100.dp))
        AuthButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 45.dp, end = 45.dp),
            onLoginPressed = onLoginPressed,
            text = stringResource(R.string.login_button_text),
            isEnabled = state.value.loginButtonEnabled,
            isExecuting = state.value.isPerformingLogin
        )
    }

    val context = LocalContext.current
    val errorMessage = state.value.errorMessage
    if (errorMessage != null) {
        val message = stringResource(id = errorMessage)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        onErrorShown()
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun PasswordInput(
    modifier: Modifier = Modifier,
    password: String,
    isPasswordError: Boolean,
    onPasswordChanged: (String) -> Unit,
    onLoginPressed: () -> Unit,
    isLoggingIn: Boolean,
    placeholder: String
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        modifier = modifier.height(30.dp),
        value = password,
        onValueChange = onPasswordChanged,
        singleLine = true,
        enabled = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None
        else PasswordVisualTransformation(),
        interactionSource = interactionSource,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                if (!isLoggingIn) {
                    onLoginPressed()
                    keyboardController?.hide()
                }
            },
        ),
    ) {
        TextFieldDefaults.TextFieldDecorationBox(
            value = password,
            innerTextField = it,
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) {
                            ImageVector.vectorResource(R.drawable.unhide)
                        } else {
                            ImageVector.vectorResource(R.drawable.hide)
                        },
                        contentDescription = stringResource(
                            id = if (passwordVisible) R.string.login_title else R.string.login_title
                        )
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
            placeholder = {
                InputTextFieldPlaceholder(
                    modifier = Modifier.padding(start = 36.dp),
                    text = placeholder
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = OnlineShopTheme.colors.inputTextContainer,
                textColor = Black,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = OnlineShopTheme.colors.error
            ),
            enabled = true,
            shape = OnlineShopTheme.shapes.medium,
            interactionSource = interactionSource,
            contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                top = 2.dp, bottom = 2.dp
            ),
            isError = isPasswordError
        )
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    val state = MutableStateFlow(UIState.empty).collectAsState()

    LoginScreen(
        state = state,
        onFirstNameChanged = { },
        onPasswordChanged = { },
        onLoginPressed = { },
        onErrorShown = { }
    )
}

@Preview
@Composable
fun PreviewPasswordInput() {
    PasswordInput(
        modifier = Modifier
            .padding(start = 45.dp, end = 45.dp)
            .fillMaxWidth(),
        password = "",
        isPasswordError = false,
        onPasswordChanged = {},
        onLoginPressed = {},
        isLoggingIn = true,
        placeholder = "Password"
    )
}
