package karim.gabbasov.catalog.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.catalog.R
import karim.gabbasov.ui.theme.Black
import karim.gabbasov.ui.theme.OnlineShopTheme
import karim.gabbasov.ui.theme.SearchPlaceholder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchView(state: MutableState<TextFieldValue>) {
    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 60.dp)
            .wrapContentHeight(),
        value = state.value,
        onValueChange = { value -> state.value = value },
        singleLine = true,
        enabled = true,
        interactionSource = interactionSource
    ) {
        TextFieldDefaults.TextFieldDecorationBox(
            value = state.value.text,
            innerTextField = it,
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            placeholder = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.search_placeholder),
                        color = SearchPlaceholder,
                        style = OnlineShopTheme.typography.labelSmall,
                        textAlign = TextAlign.Center
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "",
                            modifier = Modifier
                                .size(12.dp)
                        )
                    }
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = OnlineShopTheme.colors.inputTextContainer,
                textColor = Black,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp),
            contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                top = 2.dp, bottom = 2.dp
            ),
            enabled = true,
            interactionSource = interactionSource
        )
    }
}

@Composable
private fun ProductsListItem(product: String) {
    Text(
        text = product,
        style = OnlineShopTheme.typography.mediumProductName,
        color = Black
    )
}

@Composable
internal fun FoundProductList(
    state: MutableState<TextFieldValue>,
    searchState: List<String?>,
    onSearchInput: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 20.dp)
    ) {
        val input = state.value.text
        onSearchInput.invoke(input)
        if (input.isNotBlank()) {
            items(searchState) { product ->
                product?.let { ProductsListItem(product = it) }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSearchView() {
    val state = remember { mutableStateOf(TextFieldValue("")) }
    SearchView(state)
}
