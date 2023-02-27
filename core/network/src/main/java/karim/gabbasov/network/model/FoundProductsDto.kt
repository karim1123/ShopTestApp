package karim.gabbasov.network.model

import com.squareup.moshi.Json

data class FoundProductsDto(
    @field:Json(name = "words")
    val products: List<String>
)
