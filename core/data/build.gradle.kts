plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "karim.gabbasov.data"
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
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:model"))
    implementation(project(":core:network"))

    implementation(Libs.sandwich)
    implementation(Libs.hiltAndroid)
    kapt(Libs.hiltAndroidCompiler)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    testImplementation(Libs.junit4)
    androidTestImplementation(Libs.junit4Ext)
    testImplementation(Libs.mockk)
    testImplementation(Libs.coroutinesTest)
}
