package karim.gabbasov.product_details.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import karim.gabbasov.feature_api.NavigationRoute
import karim.gabbasov.feature_api.features.ProductDetailsFeatureApi
import karim.gabbasov.product_details.ui.ProductDetailsScreenRoute

class ProductDetailsNavigation : ProductDetailsFeatureApi {

    private val route = NavigationRoute.PRODUCT_DETAILS.route

    override fun productDetailsRoute() = route

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route) {
            ProductDetailsScreenRoute(
                navController = navController
            )
        }
    }
}
