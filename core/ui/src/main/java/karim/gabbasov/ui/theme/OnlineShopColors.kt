package karim.gabbasov.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class OnlineShopColors(
    background: Color,
    title: Color,
    inputTextContainer: Color,
    inputTextPlaceholder: Color,
    enabledButton: Color,
    buttonText: Color,
    error: Color,
    selectedBottomBarItem: Color,
    unselectedBottomBarItem: Color,
    categoryTitle: Color,
    sectionTitle: Color,
    isLight: Boolean,
) {
    var background by mutableStateOf(background)
        private set

    var title by mutableStateOf(title)
        private set

    var inputTextContainer by mutableStateOf(inputTextContainer)
        private set

    var inputTextPlaceholder by mutableStateOf(inputTextPlaceholder)
        private set

    var enabledButton by mutableStateOf(enabledButton)
        private set

    var buttonText by mutableStateOf(buttonText)
        private set

    var selectedBottomBarItem by mutableStateOf(selectedBottomBarItem)
        private set

    var unselectedBottomBarItem by mutableStateOf(unselectedBottomBarItem)
        private set

    var categoryTitle by mutableStateOf(categoryTitle)
        private set

    var sectionTitle by mutableStateOf(sectionTitle)
        private set

    var error by mutableStateOf(error)
        private set

    var isLight by mutableStateOf(isLight)
        private set

    fun copy(
        background: Color = this.background,
        title: Color = this.title,
        inputTextContainer: Color = this.inputTextContainer,
        inputTextPlaceholder: Color = this.inputTextPlaceholder,
        enabledButton: Color = this.enabledButton,
        buttonText: Color = this.buttonText,
        selectedBottomBarItem: Color = this.selectedBottomBarItem,
        unselectedBottomBarItem: Color = this.unselectedBottomBarItem,
        categoryTitle: Color = this.categoryTitle,
        sectionTitle: Color = this.sectionTitle,
        error: Color = this.error,
        isLight: Boolean = this.isLight
    ) = OnlineShopColors(
        background = background,
        title = title,
        inputTextContainer = inputTextContainer,
        inputTextPlaceholder = inputTextPlaceholder,
        enabledButton = enabledButton,
        buttonText = buttonText,
        selectedBottomBarItem = selectedBottomBarItem,
        unselectedBottomBarItem = unselectedBottomBarItem,
        categoryTitle = categoryTitle,
        sectionTitle = sectionTitle,
        error = error,
        isLight = isLight
    )

    fun updateColorsFrom(other: OnlineShopColors) {
        background = other.background
        title = other.title
        inputTextContainer = other.inputTextContainer
        inputTextPlaceholder = other.inputTextPlaceholder
        enabledButton = other.enabledButton
        buttonText = other.buttonText
        selectedBottomBarItem = other.selectedBottomBarItem
        unselectedBottomBarItem = other.unselectedBottomBarItem
        categoryTitle = other.categoryTitle
        sectionTitle = other.sectionTitle
        error = other.error
        isLight = other.isLight
    }
}

val LocalColors = staticCompositionLocalOf { lightColors() }
