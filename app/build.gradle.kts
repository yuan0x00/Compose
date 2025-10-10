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

}

dependencies {

}