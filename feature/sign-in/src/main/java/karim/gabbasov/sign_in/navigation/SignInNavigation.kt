package karim.gabbasov.sign_in.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import karim.gabbasov.feature_api.features.SignInFeatureApi
import karim.gabbasov.sign_in.ui.SignInScreenRoute

class SignInNavigation : SignInFeatureApi {

    private val route = "signIn"

    override fun signInRoute() = route

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route) {
            SignInScreenRoute(
                navController
            )
        }
    }
}
