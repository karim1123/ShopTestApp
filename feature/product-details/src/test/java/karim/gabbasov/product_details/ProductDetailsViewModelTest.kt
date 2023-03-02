package karim.gabbasov.product_details

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import karim.gabbasov.data.repository.shop.ShopApiResult
import karim.gabbasov.data.repository.shop.ShopRepository
import karim.gabbasov.feature_api.features.CatalogFeatureApi
import karim.gabbasov.feature_api.features.ProfileFeatureApi
import karim.gabbasov.model.data.shop.ProductDetailsEntity
import karim.gabbasov.product_details.ui.ProductDetailsViewAction
import karim.gabbasov.product_details.ui.ProductDetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductDetailsViewModelTest {

    private val shopRepository = mockk<ShopRepository>()
    private val catalogFeatureApi = mockk<CatalogFeatureApi>()
    private val profileFeatureApi = mockk<ProfileFeatureApi>()
    private lateinit var viewModel: ProductDetailsViewModel
    private val product = ProductDetailsEntity(
        name = "Reebok Classic",
        description = "Shoes inspired by 80s running shoes are still relevant today",
        rating = 4.2,
        numberOfReviews = 4000,
        price = 24,
        colors = listOf("color1", "color2", "color3"),
        imageUrls = listOf("url1", "url2", "url3")
    )

    @Before
    fun setUp() {
        clearAllMocks()
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = ProductDetailsViewModel(
            shopRepository = shopRepository,
            catalogFeatureApi = catalogFeatureApi,
            profileFeatureApi = profileFeatureApi
        )
    }

    @Test
    fun `When getRatingIcon with 1, return star_1`() = runTest {
        coEvery { shopRepository.loadProductDetails() } returns ShopApiResult.NetworkError
        val expected = R.drawable.star_1

        val result = viewModel.getRatingIcon(0.8)

        assertEquals(expected, result)
    }

    @Test
    fun `When getRatingIcon with 2, return star_2`() = runTest {
        coEvery { shopRepository.loadProductDetails() } returns ShopApiResult.NetworkError
        val expected = R.drawable.star_2

        val result = viewModel.getRatingIcon(1.8)

        assertEquals(expected, result)
    }

    @Test
    fun `When getRatingIcon with 3, return star_3`() = runTest {
        coEvery { shopRepository.loadProductDetails() } returns ShopApiResult.NetworkError
        val expected = R.drawable.star_3

        val result = viewModel.getRatingIcon(3.4)

        assertEquals(expected, result)
    }

    @Test
    fun `When getRatingIcon with 4, return star_4`() = runTest {
        coEvery { shopRepository.loadProductDetails() } returns ShopApiResult.NetworkError
        val expected = R.drawable.star_4

        val result = viewModel.getRatingIcon(4.4)

        assertEquals(expected, result)
    }

    @Test
    fun `When getRatingIcon with 5, return star_5`() = runTest {
        coEvery { shopRepository.loadProductDetails() } returns ShopApiResult.NetworkError
        val expected = R.drawable.star_5

        val result = viewModel.getRatingIcon(4.9)

        assertEquals(expected, result)
    }

    @Test
    fun `When getRatingIcon with unexpected value, return star_1`() = runTest {
        coEvery { shopRepository.loadProductDetails() } returns ShopApiResult.NetworkError
        val expected = R.drawable.star_1

        val result = viewModel.getRatingIcon(12.0)

        assertEquals(expected, result)
    }

    @Test
    fun `When ProductImageChanged, Then updated selectedImageIndex is emitted`() {
        val expected = 3
        assertEquals(0, viewModel.uiState.value.selectedImageIndex)

        viewModel.execute(ProductDetailsViewAction.ProductImageChanged(3))

        assertEquals(expected, viewModel.uiState.value.selectedImageIndex)
    }

    @Test
    fun `When ProductColorChanged, Then updated selectedColorIndex is emitted`() =
        runTest {
            val expected = 3
            assertEquals(0, viewModel.uiState.value.selectedColorIndex)

            viewModel.execute(ProductDetailsViewAction.ProductColorChanged(3))

            assertEquals(expected, viewModel.uiState.value.selectedColorIndex)
        }

    @Test
    fun `When QuantityChanged with increase true, Then updated selectedColorIndex is emitted`() =
        runTest {
            val expectedQuantity = 3
            val expectedSum = 72
            coEvery { shopRepository.loadProductDetails() } returns ShopApiResult.Success(
                products = null,
                productDetails = product
            )
            viewModel.getProductDetails()
            assertEquals(0, viewModel.uiState.value.quantity)

            viewModel.execute(ProductDetailsViewAction.QuantityChanged(true))
            viewModel.execute(ProductDetailsViewAction.QuantityChanged(true))
            viewModel.execute(ProductDetailsViewAction.QuantityChanged(true))

            assertEquals(expectedQuantity, viewModel.uiState.value.quantity)
            assertEquals(expectedSum, viewModel.uiState.value.purchaseSum)
        }

    @Test
    fun `When QuantityChanged with increase false, Then updated selectedColorIndex is emitted`() =
        runTest {
            val expectedQuantity = 1
            val expectedSum = 24
            coEvery { shopRepository.loadProductDetails() } returns ShopApiResult.Success(
                products = null,
                productDetails = product
            )
            viewModel.getProductDetails()
            assertEquals(0, viewModel.uiState.value.quantity)
            viewModel.execute(ProductDetailsViewAction.QuantityChanged(true))
            viewModel.execute(ProductDetailsViewAction.QuantityChanged(true))

            viewModel.execute(ProductDetailsViewAction.QuantityChanged(false))

            assertEquals(expectedQuantity, viewModel.uiState.value.quantity)
            assertEquals(expectedSum, viewModel.uiState.value.purchaseSum)
        }

    @Test
    fun `When increase false ande quantity = 0 , Then updated selectedColorIndex is not emitted`() =
        runTest {
            val expectedQuantity = 0
            val expectedSum = 0
            coEvery { shopRepository.loadProductDetails() } returns ShopApiResult.Success(
                products = null,
                productDetails = product
            )
            viewModel.getProductDetails()
            assertEquals(0, viewModel.uiState.value.quantity)

            viewModel.execute(ProductDetailsViewAction.QuantityChanged(false))

            assertEquals(expectedQuantity, viewModel.uiState.value.quantity)
            assertEquals(expectedSum, viewModel.uiState.value.purchaseSum)
        }

    @Test
    fun `When getProductDetails return NetworkError, Then updated isNetworkError is emitted`() =
        runTest {
            coEvery { shopRepository.loadProductDetails() } returns ShopApiResult.NetworkError
            viewModel.getProductDetails()

            assertTrue(viewModel.uiState.value.isNetworkError)
            assertFalse(viewModel.uiState.value.isLoading)
        }

    @Test
    fun `When getProductDetails return Success(null), Then updated isNetworkError is emitted`() =
        runTest {
            coEvery { shopRepository.loadProductDetails() } returns ShopApiResult.Success(
                null,
                null
            )
            viewModel.getProductDetails()

            assertTrue(viewModel.uiState.value.isNetworkError)
            assertFalse(viewModel.uiState.value.isLoading)
        }

    @Test
    fun `When getProductDetails return Success, Then updated product data is emitted`() =
        runTest {
            val delta = 0.0
            coEvery { shopRepository.loadProductDetails() } returns ShopApiResult.Success(
                products = null,
                productDetails = product
            )
            viewModel.getProductDetails()

            assertEquals(product.name, viewModel.uiState.value.name)
            assertEquals(product.description, viewModel.uiState.value.description)
            assertEquals(product.rating, viewModel.uiState.value.rating, delta)
            assertEquals(product.numberOfReviews, viewModel.uiState.value.numberOfReviews)
            assertEquals(product.price, viewModel.uiState.value.price)
            assertEquals(product.colors, viewModel.uiState.value.colors)
            assertEquals(product.imageUrls, viewModel.uiState.value.imageUrls)
        }
}
