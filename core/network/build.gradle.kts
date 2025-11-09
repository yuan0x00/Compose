plugins {
    alias(libs.plugins.local.android.library)
}

android {
    namespace = "com.rapid.compose.core.network"

    buildFeatures {
        buildConfig = true
    }

}

dependencies {
    api(libs.retrofit)
    api(libs.kotlinx.serialization)
    api(libs.retrofit.serialization)
    api(libs.retrofit2.kotlinx.serialization.converter)
    api(libs.logging.interceptor)
}