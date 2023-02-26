package karim.gabbasov.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object OnlineShopTheme {

    val colors: OnlineShopColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: OnlineShopTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: OnlineShopShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current
}
