package karim.gabbasov.catalog.ui

import karim.gabbasov.model.data.shop.CatalogEntity

internal sealed interface UIState {

    class Success(val catalogData: CatalogEntity?) : UIState

    object Loading : UIState

    object Error : UIState
}
