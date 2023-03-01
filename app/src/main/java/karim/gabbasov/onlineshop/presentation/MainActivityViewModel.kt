package karim.gabbasov.onlineshop.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import karim.gabbasov.data.repository.auth.AuthRepository
import karim.gabbasov.feature_api.features.CatalogFeatureApi
import karim.gabbasov.feature_api.features.LoginFeatureApi
import karim.gabbasov.feature_api.features.ProfileFeatureApi
import karim.gabbasov.feature_api.features.SignInFeatureApi
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    val signInAPi: SignInFeatureApi,
    val loginApi: LoginFeatureApi,
    val catalogApi: CatalogFeatureApi,
    val profileApi: ProfileFeatureApi
) : ViewModel() {

    fun getStartDestination(): String {
        return if (authRepository.isAuthorize()) {
            catalogApi.catalogRoute()
        } else signInAPi.signInRoute()
    }
}
