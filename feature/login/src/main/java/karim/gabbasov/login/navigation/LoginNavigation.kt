package karim.gabbasov.login.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import karim.gabbasov.feature_api.NavigationRoute
import karim.gabbasov.feature_api.features.LoginFeatureApi
import karim.gabbasov.login.ui.LoginScreenRoute

class LoginNavigation : LoginFeatureApi {

    private val route = NavigationRoute.LOGIN.route

    override fun loginRoute() = route

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route) {
            LoginScreenRoute(
                navController = navController
            )
        }
    }
}
