plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "karim.gabbasov.ui"
    compileSdk = (DefaultConfig.compileSdk)

    defaultConfig {
        minSdk = (DefaultConfig.minSdk)
        multiDexEnabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
}

dependencies {
    implementation(project(":core:model"))

    implementation(Libs.composeUi)
    implementation(Libs.composeMaterial3)
    implementation(Libs.composeUiTooling)
    implementation(Libs.accompanistPermissions)
    debugImplementation(Libs.composeTestingUiTooling)

    implementation(Libs.hiltAndroid)
    kapt(Libs.hiltAndroidCompiler)
    implementation(Libs.hiltNavigationCompose)
}
