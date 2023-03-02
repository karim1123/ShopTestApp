package karim.gabbasov.data

import com.skydoves.sandwich.ApiResponse
import io.mockk.coEvery
import io.mockk.mockk
import karim.gabbasov.data.repository.mapper.ProductsDtoMapper
import karim.gabbasov.data.repository.shop.ShopApiResult
import karim.gabbasov.data.repository.shop.ShopRepositoryImpl
import karim.gabbasov.network.ShopApi
import karim.gabbasov.network.model.FlashSaleDto
import karim.gabbasov.network.model.FoundProductsDto
import karim.gabbasov.network.model.LatestDto
import karim.gabbasov.network.model.ProductDetailsDto
import karim.gabbasov.network.model.ProductDetailsDto.Companion.toProductDetailsEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class ShopRepositoryImplTest {

    private val api = mockk<ShopApi>()
    private val mapper = mockk<ProductsDtoMapper>()
    private val repository = ShopRepositoryImpl(api, mapper)

    @Test
    fun `loadProducts should return Error when getLatestProducts response is a Failure Error`() =
        runTest {
            val responseBody = mockk<ResponseBody>()
            val mediaType = mockk<MediaType>()
            coEvery { responseBody.contentType() } returns mediaType
            coEvery { responseBody.contentLength() } returns 1
            val response = Response.error<LatestDto>(404, responseBody)
            val apiLatest = ApiResponse.Failure.Error(response)
            val flashSaleDto = mockk<FlashSaleDto>()
            val apiDiscounted = ApiResponse.Success(Response.success(flashSaleDto))
            coEvery { api.getLatestProducts() } returns apiLatest
            coEvery { api.getDiscountedProducts() } returns apiDiscounted

            val result = repository.loadProducts()

            assertEquals(ShopApiResult.NetworkError, result)
        }

    @Test
    fun `loadProducts should return Error when getDiscountedProducts is a Failure Error`() =
        runTest {
            val responseBody = mockk<ResponseBody>()
            val mediaType = mockk<MediaType>()
            coEvery { responseBody.contentType() } returns mediaType
            coEvery { responseBody.contentLength() } returns 1
            val response = Response.error<FlashSaleDto>(404, responseBody)
            val latestDto = mockk<LatestDto>()
            val apiLatest = ApiResponse.Success(Response.success(latestDto))
            val apiDiscounted = ApiResponse.Failure.Error(response)
            coEvery { api.getLatestProducts() } returns apiLatest
            coEvery { api.getDiscountedProducts() } returns apiDiscounted

            val result = repository.loadProducts()

            assertEquals(ShopApiResult.NetworkError, result)
        }

    @Test
    fun `loadProducts should return Error when both response is a Failure Error`() =
        runTest {
            val responseBody = mockk<ResponseBody>()
            val mediaType = mockk<MediaType>()
            coEvery { responseBody.contentType() } returns mediaType
            coEvery { responseBody.contentLength() } returns 1
            val responseDiscounted = Response.error<FlashSaleDto>(404, responseBody)
            val responseLatest = Response.error<LatestDto>(404, responseBody)
            val apiLatest = ApiResponse.Failure.Error(responseLatest)
            val apiDiscounted = ApiResponse.Failure.Error(responseDiscounted)
            coEvery { api.getLatestProducts() } returns apiLatest
            coEvery { api.getDiscountedProducts() } returns apiDiscounted

            val result = repository.loadProducts()

            assertEquals(ShopApiResult.NetworkError, result)
        }

    @Test
    fun `loadProducts should return Error when getDiscountedProducts response body is empty`() =
        runTest {
            val responseBody = mockk<ResponseBody>()
            val mediaType = mockk<MediaType>()
            coEvery { responseBody.contentType() } returns mediaType
            coEvery { responseBody.contentLength() } returns 1
            val flashSaleDto = null
            val latestDto = mockk<LatestDto>()
            val apiLatest = ApiResponse.Success(Response.success(latestDto))
            val apiDiscounted = ApiResponse.Success(Response.success(flashSaleDto))
            coEvery { api.getLatestProducts() } returns apiLatest
            coEvery { api.getDiscountedProducts() } returns apiDiscounted

            val result = repository.loadProducts()

            assertEquals(ShopApiResult.NetworkError, result)
        }

    @Test
    fun `loadProducts should return Error when getLatestProducts response body is empty`() =
        runTest {
            val responseBody = mockk<ResponseBody>()
            val mediaType = mockk<MediaType>()
            coEvery { responseBody.contentType() } returns mediaType
            coEvery { responseBody.contentLength() } returns 1
            val flashSaleDto = mockk<FlashSaleDto>()
            val latestDto = null
            val apiLatest = ApiResponse.Success(Response.success(latestDto))
            val apiDiscounted = ApiResponse.Success(Response.success(flashSaleDto))
            coEvery { api.getLatestProducts() } returns apiLatest
            coEvery { api.getDiscountedProducts() } returns apiDiscounted

            val result = repository.loadProducts()

            assertEquals(ShopApiResult.NetworkError, result)
        }

    @Test
    fun `loadProducts should return Error when both responses bodies are empty`() =
        runTest {
            val responseBody = mockk<ResponseBody>()
            val mediaType = mockk<MediaType>()
            coEvery { responseBody.contentType() } returns mediaType
            coEvery { responseBody.contentLength() } returns 1
            val flashSaleDto = null
            val latestDto = null
            val apiLatest = ApiResponse.Success(Response.success(latestDto))
            val apiDiscounted = ApiResponse.Success(Response.success(flashSaleDto))
            coEvery { api.getLatestProducts() } returns apiLatest
            coEvery { api.getDiscountedProducts() } returns apiDiscounted

            val result = repository.loadProducts()

            assertEquals(ShopApiResult.NetworkError, result)
        }

    @Test
    fun `findProducts should return list when input lowercase`() =
        runTest {
            val expected = listOf("Sony Playstation", "Jack Daniels", "Reebok Classic")
            val responseBody = mockk<ResponseBody>()
            val mediaType = mockk<MediaType>()
            coEvery { responseBody.contentType() } returns mediaType
            coEvery { responseBody.contentLength() } returns 1
            val dto = FoundProductsDto(
                products = listOf("Sony Playstation", "Jack Daniels", "Reebok Classic", "Puma")
            )
            val apiResponse = ApiResponse.Success(Response.success(dto))
            coEvery { api.getProductsNames() } returns apiResponse

            val result = repository.findProducts("s")

            assertEquals(expected, result)
        }

    @Test
    fun `findProducts should return list when input uppercase`() =
        runTest {
            val expected = listOf("Sony Playstation", "Jack Daniels", "Reebok Classic")
            val responseBody = mockk<ResponseBody>()
            val mediaType = mockk<MediaType>()
            coEvery { responseBody.contentType() } returns mediaType
            coEvery { responseBody.contentLength() } returns 1
            val dto = FoundProductsDto(
                products = listOf("Sony Playstation", "Jack Daniels", "Reebok Classic", "Puma")
            )
            val apiResponse = ApiResponse.Success(Response.success(dto))
            coEvery { api.getProductsNames() } returns apiResponse

            val result = repository.findProducts("S")

            assertEquals(expected, result)
        }

    @Test
    fun `loadProductDetails return Error when getProductsDetails response is a Failure Error`() =
        runTest {
            val responseBody = mockk<ResponseBody>()
            val mediaType = mockk<MediaType>()
            coEvery { responseBody.contentType() } returns mediaType
            coEvery { responseBody.contentLength() } returns 1
            val response = Response.error<ProductDetailsDto>(404, responseBody)
            val apiProductDetails = ApiResponse.Failure.Error(response)

            coEvery { api.getProductsDetails() } returns apiProductDetails

            val result = repository.loadProductDetails()

            assertEquals(ShopApiResult.NetworkError, result)
        }

    @Test
    fun `loadProductDetails return Error when getProductsDetails response body is a null`() =
        runTest {
            val responseBody = mockk<ResponseBody>()
            val mediaType = mockk<MediaType>()
            coEvery { responseBody.contentType() } returns mediaType
            coEvery { responseBody.contentLength() } returns 1
            val productDetailsDto = null
            val apiProductDetails = ApiResponse.Success(Response.success(productDetailsDto))
            coEvery { api.getProductsDetails() } returns apiProductDetails

            val result = repository.loadProductDetails()

            assertEquals(ShopApiResult.NetworkError, result)
        }

    @Test
    fun `loadProductDetails return Success when getProductsDetails return Success`() =
        runTest {
            val responseBody = mockk<ResponseBody>()
            val mediaType = mockk<MediaType>()
            coEvery { responseBody.contentType() } returns mediaType
            coEvery { responseBody.contentLength() } returns 1
            val productDetailsDto = ProductDetailsDto(
                name = "name",
                description = "description",
                rating = 4.2,
                numberOfReviews = 4000,
                price = 24,
                colors = listOf("color1", "color2", "color3"),
                imageUrls = listOf("url1", "url2", "url3")
            )
            val apiProductDetails = ApiResponse.Success(Response.success(productDetailsDto))
            coEvery { api.getProductsDetails() } returns apiProductDetails

            val result = repository.loadProductDetails()

            assertEquals(
                ShopApiResult.Success(
                    products = null,
                    productDetails = productDetailsDto.toProductDetailsEntity()
                ),
                result
            )
        }
}
