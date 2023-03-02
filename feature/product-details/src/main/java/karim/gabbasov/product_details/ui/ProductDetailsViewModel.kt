package karim.gabbasov.product_details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import karim.gabbasov.data.repository.shop.ShopApiResult
import karim.gabbasov.data.repository.shop.ShopRepository
import karim.gabbasov.feature_api.features.CatalogFeatureApi
import karim.gabbasov.feature_api.features.ProfileFeatureApi
import karim.gabbasov.product_details.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
internal class ProductDetailsViewModel @Inject constructor(
    private val shopRepository: ShopRepository,
    val catalogFeatureApi: CatalogFeatureApi,
    val profileFeatureApi: ProfileFeatureApi
) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState.empty)
    val uiState = _uiState.asStateFlow()
    private var state: UIState
        get() = _uiState.value
        set(value) {
            _uiState.update { value }
        }

    init {
        getProductDetails()
    }

    fun getProductDetails() = viewModelScope.launch {
        state = state.copy(isLoading = true)

        val response = shopRepository.loadProductDetails()
        handleRepositoryResponse(response)
    }

    fun execute(action: ProductDetailsViewAction) {
        when (action) {
            is ProductDetailsViewAction.ProductColorChanged -> {
                if (state.selectedColorIndex != action.newColorIndex) {
                    state = state.copy(selectedColorIndex = action.newColorIndex)
                }
            }
            is ProductDetailsViewAction.QuantityChanged -> {
                handleQuantityChanged(action.increaseQuantity)
            }
            is ProductDetailsViewAction.ProductImageChanged -> {
                if (state.selectedImageIndex != action.newImageIndex) {
                    state = state.copy(selectedImageIndex = action.newImageIndex)
                }
            }
        }
    }

    fun getRatingIcon(rating: Double): Int {
        return when (rating.roundToInt()) {
            1 -> R.drawable.star_1
            2 -> R.drawable.star_2
            3 -> R.drawable.star_3
            4 -> R.drawable.star_4
            5 -> R.drawable.star_5
            else -> R.drawable.star_1
        }
    }

    private fun handleQuantityChanged(increaseQuantity: Boolean) {
        if (increaseQuantity) {
            val quantity = state.quantity + 1
            state = state.copy(
                quantity = quantity,
                purchaseSum = state.price * quantity
            )
        } else {
            if (state.quantity > 0) {
                val quantity = state.quantity - 1
                state = state.copy(
                    quantity = quantity,
                    purchaseSum = state.price * quantity
                )
            }
        }
    }

    private fun handleRepositoryResponse(response: ShopApiResult) {
        when (response) {
            is ShopApiResult.Success -> {
                val productDetails = response.productDetails
                state = if (productDetails != null) {
                    state.copy(
                        isLoading = false,
                        isNetworkError = false,
                        name = productDetails.name,
                        description = productDetails.description,
                        rating = productDetails.rating,
                        numberOfReviews = productDetails.numberOfReviews,
                        price = productDetails.price,
                        colors = productDetails.colors,
                        imageUrls = productDetails.imageUrls

                    )
                } else {
                    state.copy(isNetworkError = true, isLoading = false)
                }
            }
            ShopApiResult.NetworkError -> {
                state = state.copy(isNetworkError = true, isLoading = false)
            }
        }
    }
}
