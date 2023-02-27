package karim.gabbasov.model.data.shop

data class ProductEntity(
    val category: String,
    val name: String,
    val price: Double,
    val discount: Int?,
    val imageUrl: String
)
