package karim.gabbasov.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class OnlineShopTypography(
    val titleLarge: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 26.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.3).sp
    ),
    val labelSmall: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 13.sp,
        letterSpacing = (-0.3).sp
    ),
    val labelMedium: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 15.sp,
        letterSpacing = (-0.3).sp
    ),
    val labelLarge: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 17.sp,
        letterSpacing = (-0.3).sp
    ),
    val bodySmall: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 12.sp,
        letterSpacing = (-0.3).sp
    ),
    val appBarTitle: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.3).sp
    ),
    val categoryTitle: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 8.sp,
        lineHeight = 12.sp,
        letterSpacing = (-0.3).sp
    ),
    val sectionTitle: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 23.sp,
        letterSpacing = (-0.3).sp
    ),
    val viewAllTitle: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 9.sp,
        lineHeight = 14.sp,
        letterSpacing = (-0.3).sp
    ),
    val smallCategoryTitle: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 6.sp,
        lineHeight = 9.sp,
        letterSpacing = (-0.3).sp
    ),
    val smallProductName: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 9.sp,
        lineHeight = 14.sp,
        letterSpacing = (-0.3).sp
    ),
    val smallProductPrice: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 7.sp,
        lineHeight = 11.sp,
        letterSpacing = (-0.3).sp
    ),
    val saleTitle: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        lineHeight = 15.sp,
        letterSpacing = (-0.3).sp
    ),
    val mediumCategoryTitle: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 9.sp,
        lineHeight = 14.sp,
        letterSpacing = (-0.3).sp
    ),
    val mediumProductName: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.3).sp
    ),
    val mediumProductPrice: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        lineHeight = 15.sp,
        letterSpacing = (-0.3).sp
    ),
    val profileTopBarTitle: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.3).sp
    ),
    val menuItemBody: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 17.sp,
        letterSpacing = (-0.3).sp
    ),
    val userInfo: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.3).sp
    ),
    val productDetailsName: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.3).sp
    ),
    val productDetailsPrice: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.3).sp
    ),
    val productDetailsDescription: TextStyle = TextStyle(
        fontSize = 9.sp,
        lineHeight = 14.sp,
        letterSpacing = (-0.3).sp
    ),
    val purchaseButton: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 8.sp,
        lineHeight = 12.sp,
        letterSpacing = (-0.3).sp
    )
)

val LocalTypography = staticCompositionLocalOf { OnlineShopTypography() }
