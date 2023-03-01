package karim.gabbasov.feature_api.features

import karim.gabbasov.feature_api.FeatureApi

interface ProfileFeatureApi : FeatureApi {

    fun profileRoute(): String
}
