package karim.gabbasov.network

import com.skydoves.sandwich.ApiResponse
import karim.gabbasov.network.model.FlashSaleDto
import karim.gabbasov.network.model.FoundProductsDto
import karim.gabbasov.network.model.LatestDto
import retrofit2.http.GET

interface ShopApi {

    @GET(
        "/v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7"
    )
    suspend fun getLatestProducts(): ApiResponse<LatestDto>

    @GET(
        "/v3/a9ceeb6e-416d-4352-bde6-2203416576ac"
    )
    suspend fun getDiscountedProducts(): ApiResponse<FlashSaleDto>

    @GET(
        "/v3/4c9cd822-9479-4509-803d-63197e5a9e19?word=f"
    )
    suspend fun getProductsNames(): ApiResponse<FoundProductsDto>
}
