package karim.gabbasov.catalog.ui

import karim.gabbasov.model.data.shop.CatalogEntity

internal sealed interface UiState {

    class Success(val catalogData: CatalogEntity?) : UiState

    object Loading : UiState

    object Error : UiState
}
