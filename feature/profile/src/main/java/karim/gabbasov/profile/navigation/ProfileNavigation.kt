package karim.gabbasov.profile.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import karim.gabbasov.feature_api.NavigationRoute
import karim.gabbasov.feature_api.features.ProfileFeatureApi
import karim.gabbasov.profile.ui.ProfileScreenRoute

class ProfileNavigation : ProfileFeatureApi {

    private val route = NavigationRoute.PROFILE.route

    override fun profileRoute() = route

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route) {
            ProfileScreenRoute(
                navController = navController
            )
        }
    }
}
