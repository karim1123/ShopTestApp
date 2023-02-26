object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object DefaultConfig {
    const val appId = "karim.gabbasov.onlineshop"
    const val minSdk = 24
    const val targetSdk = 33
    const val compileSdk = 33
}

object Versions {
    const val JETPACK_NAVIGATION_VERSION = "2.5.3"
    const val ANDROIDX_CORE_VERSION = "1.9.0"
    const val LIFECYCLE_VERSION = "2.5.1"
    const val JVM_VERSION = "1.8"

    const val COROUTINES_VERSION = "1.6.4"

    const val COMPOSE_VERSION = "1.3.2"
    const val COMPOSE_MATERIAL_VERSION = "1.3.1"
    const val COMPOSE_MATERIAL3_VERSION = "1.0.1"
    const val COMPOSE_ACTIVITY_VERSION = "1.6.1"
    const val COMPOSE_RUNTIME = "2.6.0-beta01"
    const val COMPOSE_NAVIGATION = "2.5.3"
    const val PERMISSIONS = "0.28.0"

    const val HILT_VERSION = "2.44.2"
    const val HILT_COMPILER_VERSION = "1.0.0"
    const val HILT_NAVIGATION_COMPOSE_VERSION = "1.0.0"
    const val HILT_LIFECYCLE_VERSION = "1.0.0-alpha03"

    const val GMS_LOCATION_VERSION = "21.0.1"

    const val RETROFIT_VERSION = "2.9.0"
    const val LOGGING_INTERCEPTOR_VERSION = "5.0.0-alpha.3"

    const val ROOM_VERSION = "2.5.0"

    const val SANDWICH_VERSION = "1.3.3"

    const val JUNIT4_VERSION = "4.13.2"
    const val JUNIT4_EXT_VERSION = "1.1.5"

    const val MOCKK_VERSION = "1.13.3"

    const val ROBOLECTRIC_VERSION = "4.9.2"

    const val LEAK_CANARY = "2.10"
}

object Libs {
    // Jetpack Compose
    const val composeUi = "androidx.compose.ui:ui:${Versions.COMPOSE_VERSION}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.COMPOSE_MATERIAL_VERSION}"
    const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.COMPOSE_MATERIAL3_VERSION}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE_VERSION}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.COMPOSE_ACTIVITY_VERSION}"
    const val composeLifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.LIFECYCLE_VERSION}"
    const val accompanistPermissions = "com.google.accompanist:accompanist-permissions:${Versions.PERMISSIONS}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.COMPOSE_NAVIGATION}"

    // Dagger - Hilt
    const val hiltAndroid =  "com.google.dagger:hilt-android:${Versions.HILT_VERSION}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.HILT_VERSION}"
    const val hiltLifecycleViewModel =  "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HILT_LIFECYCLE_VERSION}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.HILT_COMPILER_VERSION}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.HILT_NAVIGATION_COMPOSE_VERSION}"

    // Retrofit 2
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
    const val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT_VERSION}"
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.LOGGING_INTERCEPTOR_VERSION}"

    // Sandwich
    const val sandwich = "com.github.skydoves:sandwich:${Versions.SANDWICH_VERSION}"

    const val androidCore = "androidx.core:core-ktx:${Versions.ANDROIDX_CORE_VERSION}"
    const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_VERSION}"

    // Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.ROOM_VERSION}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.ROOM_VERSION}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.ROOM_VERSION}"

    // ||
    // TESTING
    // ||

    // Jetpack Compose
    const val composeTestingUi = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE_VERSION}"
    const val composeTestingUiTooling = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE_VERSION}"
    const val composeTestingUiManifest = "androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE_VERSION}"
    const val composeRuntime = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.COMPOSE_RUNTIME}"

    // Junit 4
    const val junit4 = "junit:junit:${Versions.JUNIT4_VERSION}"
    const val junit4Ext = "androidx.test.ext:junit:${Versions.JUNIT4_EXT_VERSION}"

    //MocKk
    const val mockk = "io.mockk:mockk:${Versions.MOCKK_VERSION}"

    // Robolectric
    const val robolectric = "org.robolectric:robolectric:${Versions.ROBOLECTRIC_VERSION}"

    // Leak Canary
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.LEAK_CANARY}"

    // Coroutines
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES_VERSION}"
}
