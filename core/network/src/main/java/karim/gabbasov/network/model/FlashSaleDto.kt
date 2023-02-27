package karim.gabbasov.network.model

import com.squareup.moshi.Json

data class FlashSaleDto(
    @field:Json(name = "flash_sale")
    val discountedProducts: List<ProductDto>
)
