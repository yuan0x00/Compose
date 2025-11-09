plugins {
    alias(libs.plugins.local.android.library)
}

android {
    namespace = "com.rapid.compose.core.data"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
}
