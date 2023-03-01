package karim.gabbasov.catalog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import karim.gabbasov.data.repository.shop.ShopApiResult
import karim.gabbasov.data.repository.shop.ShopRepository
import karim.gabbasov.data.repository.user.UserRepository
import karim.gabbasov.feature_api.features.CatalogFeatureApi
import karim.gabbasov.feature_api.features.ProfileFeatureApi
import karim.gabbasov.model.data.user.UserDomain
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CatalogViewModel @Inject constructor(
    private val shopRepository: ShopRepository,
    private val userRepository: UserRepository,
    val catalogFeatureApi: CatalogFeatureApi,
    val profileFeatureApi: ProfileFeatureApi
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Success(null))
    val uiState = _uiState.asStateFlow()

    private val _foundProduct: MutableStateFlow<List<String?>> = MutableStateFlow(mutableListOf())
    val foundProduct = _foundProduct.asStateFlow()

    val user = userRepository.currentUser
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UserDomain("", "", "")
        )

    private var searchJob: Job? = null

    init {
        loadCatalogData()
    }

    internal fun findProducts(name: String?) {
        if (!name.isNullOrEmpty()) {
            searchJob?.cancel()

            searchJob = viewModelScope.launch {
                delay(1000)
                _foundProduct.value = shopRepository.findProducts(name)
            }
        }
    }

    internal fun loadCatalogData() = viewModelScope.launch {
        _uiState.value = UiState.Loading

        val response = shopRepository.loadProducts()
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
