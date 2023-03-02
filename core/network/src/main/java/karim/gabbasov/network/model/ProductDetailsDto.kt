package karim.gabbasov.network.model

import com.squareup.moshi.Json
import karim.gabbasov.model.data.shop.ProductDetailsEntity

data class ProductDetailsDto(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "rating")
    val rating: Double,
    @field:Json(name = "number_of_reviews")
    val numberOfReviews: Int,
    @field:Json(name = "price")
    val price: Int,
    @field:Json(name = "colors")
    val colors: List<String>,
    @field:Json(name = "image_urls")
    val imageUrls: List<String>
) {
    companion object {
        fun ProductDetailsDto.toProductDetailsEntity(): ProductDetailsEntity {
            return ProductDetailsEntity(
                name = name,
                description = description,
                rating = rating,
                numberOfReviews = numberOfReviews,
                price = price,
                colors = colors,
                imageUrls = imageUrls
            )
        }
    }
}
