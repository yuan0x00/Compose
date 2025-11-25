import java.io.FileInputStream
import java.util.*

plugins {
    alias(libs.plugins.local.android.application)
}

val properties = Properties()
properties.load(FileInputStream(rootProject.file("config.properties")))

android {
    namespace = properties.getProperty("namespace")

    defaultConfig {
        applicationId = namespace
        versionCode = properties.getProperty("versionCode").toInt()
        versionName = properties.getProperty("versionName")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
    }

}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
    implementation(project(":core:webview"))
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.koin.androidx.compose)
}
