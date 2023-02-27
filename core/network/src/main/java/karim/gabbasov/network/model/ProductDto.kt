package karim.gabbasov.network.model

import com.squareup.moshi.Json
import karim.gabbasov.model.data.shop.ProductEntity

data class ProductDto(
    @field:Json(name = "category")
    val category: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "price")
    val price: Double,
    @field:Json(name = "discount")
    val discount: Int?,
    @field:Json(name = "image_url")
    val imageUrl: String
) {
    companion object {
        fun ProductDto.toProductEntity(): ProductEntity {
            return ProductEntity(
                category = category,
                name = name,
                price = price,
                discount = discount,
                imageUrl = imageUrl
            )
        }
    }
}
