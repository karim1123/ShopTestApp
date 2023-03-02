package karim.gabbasov.product_details.ui

internal data class UIState(
    val isLoading: Boolean,
    val isNetworkError: Boolean,
    val name: String,
    val description: String,
    val rating: Double,
    val numberOfReviews: Int,
    val price: Int,
    val colors: List<String>,
    val imageUrls: List<String>,
    val selectedColorIndex: Int,
    val quantity: Int,
    val purchaseSum: Int,
    val selectedImageIndex: Int
) {
    companion object {
        val empty: UIState
            get() = UIState(
                isLoading = false,
                isNetworkError = false,
                name = "",
                description = "",
                rating = 0.0,
                numberOfReviews = 0,
                price = 0,
                colors = listOf(),
                imageUrls = listOf(),
                selectedColorIndex = 0,
                quantity = 0,
                purchaseSum = 0,
                selectedImageIndex = 0
            )
    }
}
