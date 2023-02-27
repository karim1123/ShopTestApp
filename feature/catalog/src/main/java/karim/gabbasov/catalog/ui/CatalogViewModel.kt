package karim.gabbasov.catalog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import karim.gabbasov.data.repository.shop.ShopApiResult
import karim.gabbasov.data.repository.shop.ShopRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CatalogViewModel @Inject constructor(
    private val repository: ShopRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Success(null))
    val uiState = _uiState.asStateFlow()

    private val _foundProduct: MutableStateFlow<List<String?>> = MutableStateFlow(mutableListOf())
    val foundProduct = _foundProduct.asStateFlow()

    private var searchJob: Job? = null

    init {
        loadCatalogData()
    }

    internal fun findProducts(name: String?) {
        if (!name.isNullOrEmpty()) {
            searchJob?.cancel()

            searchJob = viewModelScope.launch {
                delay(1000)
                _foundProduct.value = repository.findProducts(name)
            }
        }
    }

    internal fun loadCatalogData() = viewModelScope.launch {
        _uiState.value = UiState.Loading

        val response = repository.loadProducts()
        handleRepositoryResponse(response)
    }

    private fun handleRepositoryResponse(response: ShopApiResult) {
        when (response) {
            is ShopApiResult.Success -> {
                _uiState.value = UiState.Success(response.data)
            }
            is ShopApiResult.NetworkError -> {
                _uiState.value = UiState.Error
            }
        }
    }
}
