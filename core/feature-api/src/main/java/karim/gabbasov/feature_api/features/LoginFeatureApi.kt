package karim.gabbasov.feature_api.features

import karim.gabbasov.feature_api.FeatureApi

interface LoginFeatureApi : FeatureApi {

    fun loginRoute(): String
}
