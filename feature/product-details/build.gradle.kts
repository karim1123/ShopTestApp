plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "karim.gabbasov.product_details"
    compileSdk = (DefaultConfig.compileSdk)

    defaultConfig {
        minSdk = (DefaultConfig.minSdk)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
    implementation(project(":core:data"))
    implementation(project(":core:feature-api"))
    implementation(project(":core:ui"))

    implementation(Libs.composeUi)
    implementation(Libs.composeMaterial3)
    implementation(Libs.composeUiTooling)
    implementation(Libs.composeRuntime)
    implementation(Libs.composeLifecycleViewModel)
    debugImplementation(Libs.composeTestingUiTooling)
    implementation(Libs.viewPager)

    implementation(Libs.hiltAndroid)
    kapt(Libs.hiltAndroidCompiler)
    implementation(Libs.hiltNavigationCompose)

    implementation(Libs.glide)
    annotationProcessor(Libs.glideCompiler)
    implementation(Libs.landScapist)

    testImplementation(Libs.junit4)
    androidTestImplementation(Libs.junit4Ext)
    testImplementation(Libs.mockk)
    testImplementation(Libs.coroutinesTest)
}
