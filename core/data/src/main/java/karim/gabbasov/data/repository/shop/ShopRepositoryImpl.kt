package karim.gabbasov.data.repository.shop

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnSuccess
import karim.gabbasov.data.repository.mapper.ProductsDtoMapper
import karim.gabbasov.model.data.shop.CatalogEntity
import karim.gabbasov.network.ShopApi
import karim.gabbasov.network.model.FlashSaleDto
import karim.gabbasov.network.model.LatestDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ShopRepositoryImpl @Inject constructor(
    private val api: ShopApi,
    private val mapper: ProductsDtoMapper
) : ShopRepository {

    override suspend fun loadProducts(): ShopApiResult = withContext(Dispatchers.IO) {
        val latestProducts = async { api.getLatestProducts() }
        val discountedProducts = async { api.getDiscountedProducts() }

        return@withContext handleResponses(
            latestProductsResponse = latestProducts.await(),
            discountedProductsResponse = discountedProducts.await()
        )
    }

    override suspend fun findProducts(name: String): List<String> = withContext(Dispatchers.IO) {
        val products: MutableList<String> = mutableListOf()
        api.getProductsNames().suspendOnSuccess {
            data.products.map {
                if (it.lowercase().contains(name.lowercase())) products.add(it)
            }
        }
        return@withContext products
    }

    private fun handleResponses(
        latestProductsResponse: ApiResponse<LatestDto>,
        discountedProductsResponse: ApiResponse<FlashSaleDto>
    ): ShopApiResult {
        if (
            latestProductsResponse is ApiResponse.Success &&
            discountedProductsResponse is ApiResponse.Success
        ) {
            val latestProducts = latestProductsResponse.response.body()
            val discountedProducts = discountedProductsResponse.response.body()

            if (latestProducts != null && discountedProducts != null) {
                return ShopApiResult.Success(
                    data = mapper.mapToCatalogEntity(
                        latestDto = latestProducts,
                        flashSaleDto = discountedProducts
                    )
                )
            }
        }
        return ShopApiResult.NetworkError
    }
}

sealed class ShopApiResult {
    class Success(val data: CatalogEntity) : ShopApiResult()
    object NetworkError : ShopApiResult()
}
