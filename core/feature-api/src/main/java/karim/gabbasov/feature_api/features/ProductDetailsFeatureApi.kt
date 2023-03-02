package karim.gabbasov.feature_api.features

import karim.gabbasov.feature_api.FeatureApi

interface ProductDetailsFeatureApi : FeatureApi {

    fun productDetailsRoute(): String
}
