package karim.gabbasov.data.repository.shop

interface ShopRepository {

    suspend fun loadProducts(): ShopApiResult

    suspend fun findProducts(name: String): List<String>
}
