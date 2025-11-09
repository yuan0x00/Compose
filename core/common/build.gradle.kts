plugins {
    alias(libs.plugins.local.android.library)
}

android {
    namespace = "com.rapid.compose.core.common"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

}