package karim.gabbasov.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

data class OnlineShopShapes(
    val small: CornerBasedShape = RoundedCornerShape(10.dp),
    val medium: CornerBasedShape = RoundedCornerShape(16.dp),
    val bottomBar: CornerBasedShape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp)
)

val LocalShapes = staticCompositionLocalOf { OnlineShopShapes() }
