package karim.gabbasov.product_details.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.product_details.R
import karim.gabbasov.product_details.ui.UIState
import karim.gabbasov.ui.theme.ColorPickerTitle
import karim.gabbasov.ui.theme.OnlineShopTheme

@Composable
internal fun ColorPicker(
    modifier: Modifier = Modifier,
    uiState: UIState,
    onColorChanged: (Int) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.color_title),
            style = OnlineShopTheme.typography.productDetailsDescription,
            fontWeight = FontWeight.SemiBold,
            color = ColorPickerTitle
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow {
            itemsIndexed(uiState.colors) { index, _ ->
                ColorItem(
                    onColorChanged = onColorChanged,
                    uiState = uiState,
                    index = index
                )
            }
        }
    }
}

@Composable
private fun ColorItem(
    onColorChanged: (Int) -> Unit,
    uiState: UIState,
    index: Int
) {
    val selectedColorIndex = uiState.selectedColorIndex
    val selectedColor = if (selectedColorIndex == index) {
        OnlineShopTheme.colors.selectedBottomBarItem
    } else {
        Color.Transparent
    }

    Card(
        modifier = Modifier
            .height(26.dp)
            .width(34.dp)
            .clickable { onColorChanged.invoke(index) },
        border = BorderStroke(
            width = 2.dp,
            color = selectedColor
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor(uiState.colors[index]))
        ),
    ) {}
    Spacer(modifier = Modifier.width(14.dp))
}

@Preview
@Composable
private fun PreviewColorPicker() {
    val uiState = UIState.empty.copy(
        colors = listOf("#ffffff", "#b5b5b5", "#000000")
    )
    ColorPicker(
        uiState = uiState,
        onColorChanged = {}
    )
}

@Preview
@Composable
private fun PreviewColorItem() {
    val uiState = UIState.empty.copy(colors = listOf("#ffffff"))
    ColorItem(onColorChanged = {}, uiState = uiState, index = 0)
}
