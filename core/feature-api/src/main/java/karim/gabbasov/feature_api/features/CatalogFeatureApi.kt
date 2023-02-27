package karim.gabbasov.feature_api.features

import karim.gabbasov.feature_api.FeatureApi

interface CatalogFeatureApi : FeatureApi {

    fun catalogRoute(): String
}
