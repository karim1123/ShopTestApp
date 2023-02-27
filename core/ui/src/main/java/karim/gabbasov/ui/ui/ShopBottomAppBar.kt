package karim.gabbasov.ui.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import karim.gabbasov.ui.R
import karim.gabbasov.ui.theme.OnlineShopTheme

data class BottomNavItem(
    val route: String,
    val icon: ImageVector
)

@Composable
fun ShopBottomAppBar(
    items: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomAppBar(
        modifier = Modifier.fillMaxWidth().clip(OnlineShopTheme.shapes.bottomBar),
        containerColor = OnlineShopTheme.colors.background,
        tonalElevation = 10.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = OnlineShopTheme.colors.background,
                    selectedIconColor = OnlineShopTheme.colors.selectedBottomBarItem,
                    unselectedIconColor = OnlineShopTheme.colors.unselectedBottomBarItem
                ),
                onClick = { onItemClick(item) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.route
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewShopBottomAppBar() {
    ShopBottomAppBar(
        items = listOf(
            BottomNavItem(
                route = "catalog",
                icon = ImageVector.vectorResource(R.drawable.ic_home)
            ),
            BottomNavItem(
                route = "",
                icon = ImageVector.vectorResource(R.drawable.ic_favorite)
            ),
            BottomNavItem(
                route = "",
                icon = ImageVector.vectorResource(R.drawable.ic_cart)
            ),
            BottomNavItem(
                route = "",
                icon = ImageVector.vectorResource(R.drawable.ic_chat)
            ),
            BottomNavItem(
                route = "",
                icon = ImageVector.vectorResource(R.drawable.ic_profile)
            )
        ),
        navController = NavController(LocalContext.current),
        onItemClick = {}
    )
}
