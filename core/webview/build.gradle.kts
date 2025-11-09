plugins {
    alias(libs.plugins.local.android.library)
}

android {
    namespace = "com.rapid.compose.core.webview"
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.webkit)
}