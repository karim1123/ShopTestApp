package karim.gabbasov.network.model

import com.squareup.moshi.Json

data class LatestDto(
    @field:Json(name = "latest")
    val latest: List<ProductDto>
)
