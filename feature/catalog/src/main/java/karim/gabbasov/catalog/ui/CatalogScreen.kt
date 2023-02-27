package karim.gabbasov.catalog.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import karim.gabbasov.catalog.R
import karim.gabbasov.catalog.ui.components.BrandsSection
import karim.gabbasov.catalog.ui.components.CatalogTopBar
import karim.gabbasov.catalog.ui.components.FlashSaleProductsSection
import karim.gabbasov.catalog.ui.components.FoundProductList
import karim.gabbasov.catalog.ui.components.LatestProductsSection
import karim.gabbasov.catalog.ui.components.SearchView
import karim.gabbasov.catalog.ui.utils.CatalogCategories
import karim.gabbasov.ui.R.drawable.ic_cart
import karim.gabbasov.ui.R.drawable.ic_chat
import karim.gabbasov.ui.R.drawable.ic_favorite
import karim.gabbasov.ui.R.drawable.ic_home
import karim.gabbasov.ui.R.drawable.ic_profile
import karim.gabbasov.ui.R.drawable.no_network_icon
import karim.gabbasov.ui.theme.OnlineShopTheme
import karim.gabbasov.ui.ui.BottomNavItem
import karim.gabbasov.ui.ui.ErrorCard
import karim.gabbasov.ui.ui.ShopBottomAppBar

@Composable
internal fun CatalogScreenRoute(
    navController: NavHostController,
    viewModel: CatalogViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val searchState by viewModel.foundProduct.collectAsStateWithLifecycle()

    OnlineShopTheme {
        CatalogScreen(
            modifier = Modifier,
            state = state,
            searchState = searchState,
            onSearchInput = { viewModel.findProducts(it) },
            onUpdateClick = { viewModel.loadCatalogData() },
            navController = navController
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CatalogScreen(
    modifier: Modifier = Modifier,
    state: UiState,
    searchState: List<String?>,
    onSearchInput: (String) -> Unit,
    onUpdateClick: () -> Unit,
    navController: NavHostController
) {
    Scaffold(
        containerColor = OnlineShopTheme.colors.background,
        topBar = { CatalogTopBar() },
        bottomBar = {
            ShopBottomAppBar(
                items = listOf(
                    BottomNavItem(
                        route = "catalog",
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
                        route = "",
                        icon = ImageVector.vectorResource(ic_profile)
                    )
                ),
                navController = navController,
                onItemClick = {}
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            when (state) {
                is UiState.Loading -> CircularProgressIndicator(
                    modifier = modifier.align(Alignment.Center)
                )
                is UiState.Success -> {
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
                                        latestProducts = state.catalogData.discountedProducts
                                    )
                                }
                                item {
                                    Spacer(modifier = Modifier.height(18.dp))
                                    BrandsSection()
                                }
                            } else {
                                item {
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
                is UiState.Error -> {
                    ErrorCard(
                        onClick = onUpdateClick,
                        title = stringResource(R.string.no_network_error_title),
                        errorMessage = stringResource(R.string.no_network_error_body),
                        icon = ImageVector.vectorResource(no_network_icon),
                        iconDescription = stringResource(R.string.no_network_error_title)
                    )
                }
            }
        }
    }
}

@Composable
private fun CatalogCategories() {
    LazyRow(
        content = {
            items(CatalogCategories.getCatalogCategories()) { category ->
                Category(
                    modifier = Modifier.padding(start = 10.dp),
                    category = category
                )
            }
        }
    )
}

@Composable
private fun Category(
    modifier: Modifier = Modifier,
    category: CatalogCategories
) {
    Column(
        modifier = modifier
            .clickable { }
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(42.dp)
                .padding(bottom = 10.dp),
            imageVector = ImageVector.vectorResource(category.drawableResId),
            contentDescription = stringResource(category.stringResId)
        )
        Text(
            text = stringResource(category.stringResId),
            style = OnlineShopTheme.typography.categoryTitle,
            color = OnlineShopTheme.colors.categoryTitle
        )
    }
    Spacer(modifier = Modifier.padding(start = 8.dp))
}

@Composable
internal fun CatalogSectionTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    Row(
        modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = OnlineShopTheme.colors.sectionTitle,
            style = OnlineShopTheme.typography.sectionTitle
        )
        Text(
            modifier = Modifier.clickable { },
            text = stringResource(R.string.view_all_title),
            color = OnlineShopTheme.colors.categoryTitle,
            style = OnlineShopTheme.typography.viewAllTitle
        )
    }
}

@Preview
@Composable
private fun PreviewCategory() {
    Category(
        category = CatalogCategories.CARS
    )
}

@Preview
@Composable
private fun PreviewCatalogCategories() {
    CatalogCategories
}

@Preview
@Composable
private fun PreviewCatalogSectionTitle() {
    CatalogSectionTitle(
        title = "Latest"
    )
}
