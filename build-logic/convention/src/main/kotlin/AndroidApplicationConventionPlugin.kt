import com.android.build.api.dsl.ApplicationExtension
import local.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import java.io.FileInputStream
import java.util.*

@Suppress("UNUSED")
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            val properties = Properties()
            properties.load(FileInputStream(rootProject.file("config.properties")))

            val targetSdk = properties.getProperty("targetSdk").toInt()
            extensions.configure<ApplicationExtension> {
                defaultConfig.targetSdk = targetSdk
            }

            configureAndroidCompose(extensions.getByType<ApplicationExtension>())
        }
    }
}