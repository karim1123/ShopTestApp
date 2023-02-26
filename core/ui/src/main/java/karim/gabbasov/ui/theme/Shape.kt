package karim.gabbasov.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

data class OnlineShopShapes(
    val medium: CornerBasedShape = RoundedCornerShape(16.dp)
)

val LocalShapes = staticCompositionLocalOf { OnlineShopShapes() }
