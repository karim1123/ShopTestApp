package karim.gabbasov.onlineshop.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import karim.gabbasov.catalog.navigation.CatalogNavigation
import karim.gabbasov.feature_api.features.CatalogFeatureApi
import karim.gabbasov.feature_api.features.LoginFeatureApi
import karim.gabbasov.feature_api.features.ProductDetailsFeatureApi
import karim.gabbasov.feature_api.features.ProfileFeatureApi
import karim.gabbasov.feature_api.features.SignInFeatureApi
import karim.gabbasov.login.navigation.LoginNavigation
import karim.gabbasov.product_details.navigation.ProductDetailsNavigation
import karim.gabbasov.profile.navigation.ProfileNavigation
import karim.gabbasov.sign_in.navigation.SignInNavigation
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSightApi(): SignInFeatureApi {
        return SignInNavigation()
    }

    @Provides
    @Singleton
    fun provideLoginApi(): LoginFeatureApi {
        return LoginNavigation()
    }

    @Provides
    @Singleton
    fun provideCatalogApi(): CatalogFeatureApi {
        return CatalogNavigation()
    }

    @Provides
    @Singleton
    fun provideProfileApi(): ProfileFeatureApi {
        return ProfileNavigation()
    }

    @Provides
    @Singleton
    fun provideProductDetailsApi(): ProductDetailsFeatureApi {
        return ProductDetailsNavigation()
    }
}
