package karim.gabbasov.data.repository.mapper

import karim.gabbasov.model.data.shop.CatalogEntity
import karim.gabbasov.network.model.FlashSaleDto
import karim.gabbasov.network.model.LatestDto
import karim.gabbasov.network.model.ProductDto.Companion.toProductEntity
import javax.inject.Inject

internal class ProductsDtoMapper @Inject constructor() {

    fun mapToCatalogEntity(
        latestDto: LatestDto,
        flashSaleDto: FlashSaleDto
    ): CatalogEntity {
        return CatalogEntity(
            latestProducts = latestDto.latest.map { it.toProductEntity() },
            discountedProducts = flashSaleDto.discountedProducts.map { it.toProductEntity() }
        )
    }
}
