import java.io.FileInputStream
import java.util.*

plugins {
    alias(libs.plugins.local.android.application)
}

val properties = Properties()
properties.load(FileInputStream(rootProject.file("config.properties")))

android {
    namespace = "com.rapid.compose"

    defaultConfig {
        applicationId = properties.getProperty("applicationId")
        versionCode = properties.getProperty("versionCode").toInt()
        versionName = properties.getProperty("versionName")
    }

    buildFeatures {
        buildConfig = true
    }
    signingConfigs {
        getByName("debug") {

        }
        maybeCreate("release").apply {
            storeFile = project.rootProject.file("./app/release.jks")
            storePassword = properties.getProperty("storePassword")
            keyAlias = properties.getProperty("keyAlias")
            keyPassword = properties.getProperty("keyPassword")
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
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
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
}
