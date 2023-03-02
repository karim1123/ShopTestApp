package karim.gabbasov.model.data.shop

data class ProductDetailsEntity(
    val name: String,
    val description: String,
    val rating: Double,
    val numberOfReviews: Int,
    val price: Int,
    val colors: List<String>,
    val imageUrls: List<String>
)
