package karim.gabbasov.feature_api.features

import karim.gabbasov.feature_api.FeatureApi

interface SignInFeatureApi : FeatureApi {

    fun signInRoute(): String
}
