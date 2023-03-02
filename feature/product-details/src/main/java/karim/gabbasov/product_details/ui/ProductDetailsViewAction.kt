package karim.gabbasov.product_details.ui

internal sealed interface ProductDetailsViewAction {
    data class ProductColorChanged(val newColorIndex: Int) : ProductDetailsViewAction
    data class QuantityChanged(val increaseQuantity: Boolean) : ProductDetailsViewAction
    data class ProductImageChanged(val newImageIndex: Int) : ProductDetailsViewAction
}
