package karim.gabbasov.ui.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.ui.theme.Black
import karim.gabbasov.ui.theme.OnlineShopTheme

@Composable
fun Title(
    title: String
) {
    Text(
        text = title,
        style = OnlineShopTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        color = OnlineShopTheme.colors.title
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldInput(
    modifier: Modifier = Modifier,
    text: String,
    isTextValueError: Boolean,
    onTextValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    placeholder: String
) {
    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        modifier = modifier.height(30.dp),
        value = text,
        onValueChange = onTextValueChanged,
        singleLine = true,
        enabled = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
    ) {
        TextFieldDefaults.TextFieldDecorationBox(
            value = text,
            innerTextField = it,
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            placeholder = {
                InputTextFieldPlaceholder(
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
            isError = isTextValueError
        )
    }
}

@Composable
fun InputTextFieldPlaceholder(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = text,
        color = OnlineShopTheme.colors.inputTextPlaceholder,
        style = OnlineShopTheme.typography.labelSmall,
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    onLoginPressed: () -> Unit,
    text: String,
    isEnabled: Boolean,
    isExecuting: Boolean
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Button(
        modifier = modifier.height(46.dp),
        onClick = {
            if (!isExecuting) {
                onLoginPressed.invoke()
                keyboardController?.hide()
            }
        },
        shape = OnlineShopTheme.shapes.medium,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = OnlineShopTheme.colors.enabledButton
        ),
        contentPadding = PaddingValues(top = 2.dp, bottom = 2.dp)
    ) {
        Text(
            text = text,
            style = OnlineShopTheme.typography.labelLarge,
            color = OnlineShopTheme.colors.buttonText
        )
    }
}

@Preview
@Composable
fun PreviewTitle() {
    Title(
        title = " Sign in"
    )
}

@Preview
@Composable
fun PreviewTextFieldInput() {
    val focusManager = LocalFocusManager.current
    TextFieldInput(
        modifier = Modifier
            .padding(bottom = 35.dp, start = 45.dp, end = 45.dp)
            .fillMaxWidth(),
        text = "",
        isTextValueError = false,
        onTextValueChanged = {},
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        placeholder = "First Name"
    )
}

@Preview
@Composable
fun PreviewInputTextFieldPlaceholder() {
    InputTextFieldPlaceholder(
        text = "First Name"
    )
}

@Preview
@Composable
fun PreviewAuthButton() {
    AuthButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 45.dp, end = 45.dp),
        onLoginPressed = { /*TODO*/ },
        text = "Login",
        isEnabled = true,
        isExecuting = true
    )
}
