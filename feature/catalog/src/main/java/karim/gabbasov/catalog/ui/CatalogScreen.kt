package karim.gabbasov.catalog.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import karim.gabbasov.catalog.ui.components.BrandsSection
import karim.gabbasov.catalog.ui.components.CatalogCategories
import karim.gabbasov.catalog.ui.components.CatalogTopBar
import karim.gabbasov.catalog.ui.components.FlashSaleProductsSection
import karim.gabbasov.catalog.ui.components.FoundProductList
import karim.gabbasov.catalog.ui.components.LatestProductsSection
import karim.gabbasov.catalog.ui.components.SearchView
import karim.gabbasov.model.data.shop.CatalogEntity
import karim.gabbasov.model.data.shop.ProductEntity
import karim.gabbasov.ui.R.drawable.ic_cart
import karim.gabbasov.ui.R.drawable.ic_chat
import karim.gabbasov.ui.R.drawable.ic_favorite
import karim.gabbasov.ui.R.drawable.ic_home
import karim.gabbasov.ui.R.drawable.ic_profile
import karim.gabbasov.ui.R.drawable.no_network_icon
import karim.gabbasov.ui.R.string.no_network_error_body
import karim.gabbasov.ui.R.string.no_network_error_title
import karim.gabbasov.ui.theme.OnlineShopTheme
import karim.gabbasov.ui.ui.BottomNavItem
import karim.gabbasov.ui.ui.ErrorCard
import karim.gabbasov.ui.ui.ShopBottomAppBar
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
internal fun CatalogScreenRoute(
    navController: NavHostController,
    viewModel: CatalogViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val searchState by viewModel.foundProduct.collectAsStateWithLifecycle()
    val user = viewModel.user.collectAsStateWithLifecycle()

    OnlineShopTheme {
        CatalogScreen(
            modifier = Modifier,
            state = state,
            searchState = searchState,
            onSearchInput = { viewModel.findProducts(it) },
            onUpdateClick = { viewModel.loadCatalogData() },
            navController = navController,
            userUrl = user.value?.imageUri.toString(),
            catalogNavRoute = viewModel.catalogFeatureApi.catalogRoute(),
            profileNavRoute = viewModel.profileFeatureApi.profileRoute(),
            onProductDetails = {
                if (it == "Reebok Classic") {
                    navController.navigate(
                        viewModel.productDetailsFeatureApi.productDetailsRoute()
                    ) {
                        launchSingleTop = true
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CatalogScreen(
    modifier: Modifier = Modifier,
    state: UIState,
    searchState: List<String?>,
    onSearchInput: (String) -> Unit,
    onUpdateClick: () -> Unit,
    navController: NavHostController,
    profileNavRoute: String,
    catalogNavRoute: String,
    userUrl: String,
    onProductDetails: (String) -> Unit
) {
    Scaffold(
        containerColor = OnlineShopTheme.colors.background,
        topBar = { CatalogTopBar(userUrl) },
        bottomBar = {
            ShopBottomAppBar(
                items = listOf(
                    BottomNavItem(
                        route = catalogNavRoute,
                        icon = ImageVector.vectorResource(ic_home)
                    ),
                    BottomNavItem(
                        route = "",
                        icon = ImageVector.vectorResource(ic_favorite)
                    ),
                    BottomNavItem(
                        route = "",
                        icon = ImageVector.vectorResource(ic_cart)
                    ),
                    BottomNavItem(
                        route = "",
                        icon = ImageVector.vectorResource(ic_chat)
                    ),
                    BottomNavItem(
                        route = profileNavRoute,
                        icon = ImageVector.vectorResource(ic_profile)
                    )
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            when (state) {
                is UIState.Loading -> CircularProgressIndicator(
                    modifier = modifier.align(Alignment.Center)
                )
                is UIState.Success -> {
                    if (state.catalogData != null) {
                        val inputState = remember { mutableStateOf(TextFieldValue("")) }
                        LazyColumn {
                            item {
                                Spacer(modifier = Modifier.height(10.dp))
                                SearchView(state = inputState)
                            }
                            if (inputState.value.text.isBlank()) {
                                item {
                                    Spacer(modifier = Modifier.height(20.dp))
                                    CatalogCategories()
                                }
                                item {
                                    Spacer(modifier = Modifier.height(30.dp))
                                    LatestProductsSection(
                                        latestProducts = state.catalogData.latestProducts
                                    )
                                }
                                item {
                                    Spacer(modifier = Modifier.height(18.dp))
                                    FlashSaleProductsSection(
                                        latestProducts = state.catalogData.discountedProducts,
                                        onProductDetails = onProductDetails
                                    )
                                }
                                item {
                                    Spacer(modifier = Modifier.height(18.dp))
                                    BrandsSection()
                                }
                            } else {
                                item {
                                    Spacer(modifier = Modifier.height(10.dp))
                                    FoundProductList(
                                        state = inputState,
                                        searchState = searchState,
                                        onSearchInput = onSearchInput
                                    )
                                }
                            }
                        }
                    }
                }
                is UIState.Error -> {
                    ErrorCard(
                        onClick = onUpdateClick,
                        title = stringResource(no_network_error_title),
                        errorMessage = stringResource(no_network_error_body),
                        icon = ImageVector.vectorResource(no_network_icon),
                        iconDescription = stringResource(no_network_error_title)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCatalogScreen() {
    val product = ProductEntity(
        category = "Phones",
        name = "Samsung S10",
        price = 1000.0,
        discount = 30,
        imageUrl = ""
    )
    val uiState = UIState.Success(
        CatalogEntity(
            latestProducts = listOf(product, product, product, product, product),
            discountedProducts = listOf(product, product, product)
        )
    )
    val searchState = MutableStateFlow(mutableListOf("")).collectAsState()

    CatalogScreen(
        modifier = Modifier,
        state = uiState,
        searchState = searchState.value,
        onSearchInput = {},
        onUpdateClick = {},
        navController = rememberNavController(),
        userUrl = "",
        catalogNavRoute = "",
        profileNavRoute = "",
        onProductDetails = {}
    )
}
