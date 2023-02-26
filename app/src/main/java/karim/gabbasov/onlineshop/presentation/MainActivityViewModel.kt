package karim.gabbasov.onlineshop.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import karim.gabbasov.feature_api.features.LoginFeatureApi
import karim.gabbasov.feature_api.features.SignInFeatureApi
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val signInAPi: SignInFeatureApi,
    val loginApi: LoginFeatureApi
) : ViewModel()