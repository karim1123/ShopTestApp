package karim.gabbasov.onlineshop.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import karim.gabbasov.feature_api.register
import karim.gabbasov.onlineshop.presentation.MainActivityViewModel

@Composable
fun OnlineShopNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: MainActivityViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = viewModel.signInAPi.signInRoute()
    ) {
        register(
            viewModel.signInAPi,
            navController = navController,
            modifier = modifier
        )
        register(
            viewModel.loginApi,
            navController,
            modifier
        )
        register(
            viewModel.catalogApi,
            navController,
            modifier
        )
    }
}
