package karim.gabbasov.product_details.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.product_details.R
import karim.gabbasov.ui.theme.Black

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProductDetailsAppBar(onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, start = 12.dp, end = 12.dp)
    ) {
        CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = onBack
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = stringResource(R.string.app_bar_nav_body),
                    tint = Black
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewProductDetailsAppBar() {
    ProductDetailsAppBar({})
}
