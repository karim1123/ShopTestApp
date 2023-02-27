package karim.gabbasov.model.data.shop

data class CatalogEntity(
    val latestProducts: List<ProductEntity>,
    val discountedProducts: List<ProductEntity>
)
