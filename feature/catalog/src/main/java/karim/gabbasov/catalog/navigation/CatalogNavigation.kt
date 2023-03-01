package karim.gabbasov.catalog.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import karim.gabbasov.catalog.ui.CatalogScreenRoute
import karim.gabbasov.feature_api.NavigationRoute
import karim.gabbasov.feature_api.features.CatalogFeatureApi

class CatalogNavigation : CatalogFeatureApi {

    private val route = NavigationRoute.CATALOG.route

    override fun catalogRoute() = route

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route) {
            CatalogScreenRoute(
                navController = navController
            )
        }
    }
}
