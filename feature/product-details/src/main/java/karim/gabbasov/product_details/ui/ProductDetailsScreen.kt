package karim.gabbasov.product_details.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import karim.gabbasov.product_details.R.drawable.star_2
import karim.gabbasov.product_details.ui.components.AdditionalBar
import karim.gabbasov.product_details.ui.components.ColorPicker
import karim.gabbasov.product_details.ui.components.ImageViewPager
import karim.gabbasov.product_details.ui.components.ProductDetailsAppBar
import karim.gabbasov.product_details.ui.components.ProductInfo
import karim.gabbasov.product_details.ui.components.PurchaseCard
import karim.gabbasov.ui.R
import karim.gabbasov.ui.theme.OnlineShopTheme
import karim.gabbasov.ui.ui.BottomNavItem
import karim.gabbasov.ui.ui.ErrorCard
import karim.gabbasov.ui.ui.ShopBottomAppBar

@Composable
internal fun ProductDetailsScreenRoute(
    navController: NavHostController,
    viewModel: ProductDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    OnlineShopTheme {
        ProductDetailsScreen(
            uiState = uiState,
            navController = navController,
            profileNavRoute = viewModel.profileFeatureApi.profileRoute(),
            catalogNavRoute = viewModel.catalogFeatureApi.catalogRoute(),
            onUpdateClick = { viewModel.getProductDetails() },
            ratingIcon = { viewModel.getRatingIcon(it) },
            onColorChanged = {
                viewModel.execute(ProductDetailsViewAction.ProductColorChanged(it))
            },
            onQuantityChanged = {
                viewModel.execute(ProductDetailsViewAction.QuantityChanged(it))
            },
            onImageChanged = {
                viewModel.execute(ProductDetailsViewAction.ProductImageChanged(it))
            },
            onBack = { navController.popBackStack() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductDetailsScreen(
    modifier: Modifier = Modifier,
    uiState: UIState,
    navController: NavController,
    profileNavRoute: String,
    catalogNavRoute: String,
    onUpdateClick: () -> Unit,
    ratingIcon: (Double) -> Int,
    onColorChanged: (Int) -> Unit,
    onQuantityChanged: (Boolean) -> Unit,
    onImageChanged: (Int) -> Unit,
    onBack: () -> Unit
) {

    Scaffold(
        topBar = { ProductDetailsAppBar(onBack) },
        containerColor = OnlineShopTheme.colors.background,
        bottomBar = {
            ShopBottomAppBar(
                items = listOf(
                    BottomNavItem(
                        route = catalogNavRoute,
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
                        route = profileNavRoute,
                        icon = ImageVector.vectorResource(R.drawable.ic_profile)
                    )
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route) {
                        if (it.route == catalogNavRoute) {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        } else {
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
            }
            if (uiState.isNetworkError) {
                ErrorCard(
                    onClick = onUpdateClick,
                    title = stringResource(R.string.no_network_error_title),
                    errorMessage = stringResource(R.string.no_network_error_body),
                    icon = ImageVector.vectorResource(R.drawable.no_network_icon),
                    iconDescription = stringResource(R.string.no_network_error_title)
                )
            } else {
                Column(modifier = modifier) {
                    Spacer(modifier = Modifier.height(40.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        ImageViewPager(
                            uiState = uiState,
                            onImageChanged = onImageChanged
                        )
                        AdditionalBar(
                            modifier = Modifier
                                .height(96.dp)
                                .width(42.dp)
                                .padding(end = 12.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    ProductInfo(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        uiState = uiState,
                        ratingIcon = ratingIcon(uiState.rating)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    ColorPicker(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        uiState = uiState,
                        onColorChanged = onColorChanged
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    PurchaseCard(
                        uiState = uiState,
                        onQuantityChanged = onQuantityChanged
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewProductDetailsScreen() {
    val uiState = UIState(
        isLoading = false,
        isNetworkError = false,
        name = "Reebok Classic",
        description = "Shoes inspired by 80s running shoes are still relevant today",
        rating = 4.3,
        numberOfReviews = 4000,
        price = 24,
        colors = listOf("#ffffff", "#b5b5b5", "#000000"),
        imageUrls = listOf("", "", ""),
        selectedColorIndex = 1,
        quantity = 4,
        purchaseSum = 96,
        selectedImageIndex = 0
    )
    ProductDetailsScreen(
        modifier = Modifier,
        uiState = uiState,
        navController = rememberNavController(),
        profileNavRoute = "",
        catalogNavRoute = "",
        onQuantityChanged = {},
        ratingIcon = { star_2 },
        onColorChanged = {},
        onBack = {},
        onImageChanged = {},
        onUpdateClick = {}
    )
}
