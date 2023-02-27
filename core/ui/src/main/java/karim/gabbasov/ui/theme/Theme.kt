package karim.gabbasov.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

fun lightColors() = OnlineShopColors(
    background = White,
    title = Title,
    inputTextContainer = InputTextContainer,
    inputTextPlaceholder = InputTextPlaceholder,
    enabledButton = EnabledButton,
    buttonText = ButtonText,
    selectedBottomBarItem = SelectedBottomBarItem,
    unselectedBottomBarItem = UnselectedBottomBarItem,
    categoryTitle = CategoryTitle,
    sectionTitle = SectionTitle,
    error = Color.Red,
    isLight = true
)

fun darkColors() = OnlineShopColors(
    background = White,
    title = Title,
    inputTextContainer = InputTextContainer,
    inputTextPlaceholder = InputTextPlaceholder,
    enabledButton = EnabledButton,
    buttonText = ButtonText,
    selectedBottomBarItem = SelectedBottomBarItem,
    unselectedBottomBarItem = UnselectedBottomBarItem,
    categoryTitle = CategoryTitle,
    sectionTitle = SectionTitle,
    error = Color.Red,
    isLight = false
)

@Composable
fun OnlineShopTheme(
    typography: OnlineShopTypography = OnlineShopTheme.typography,
    colors: OnlineShopColors = OnlineShopTheme.colors,
    shapes: OnlineShopShapes = OnlineShopTheme.shapes,
    darkColors: OnlineShopColors? = null,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val currentColor = remember { if (darkColors != null && darkTheme) darkColors else colors }
    val rememberedColors = remember { currentColor.copy() }.apply { updateColorsFrom(currentColor) }
    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalShapes provides shapes,
        LocalTypography provides typography,
    ) {
        ProvideTextStyle(
            typography.labelMedium.copy(color = OnlineShopTheme.colors.buttonText),
            content = content
        )
    }
}
