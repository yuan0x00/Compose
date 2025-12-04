package local

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.FileInputStream
import java.util.*

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        val properties = Properties()
        properties.load(FileInputStream(rootProject.file("config.properties")))

        compileSdk = properties.getProperty("compileSdk").toInt()

        defaultConfig {
            minSdk = properties.getProperty("minSdk").toInt()
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("kotlin").get().toString()
        }

        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()
            add("implementation", platform(bom))

            listOf(
                "androidx-core-ktx",
                "androidx-lifecycle-runtime-ktx",
                "androidx-activity-compose",
                "androidx-compose-ui",
                "androidx-compose-ui-graphics",
                "androidx-compose-ui-tooling-preview",
                "androidx-compose-material3",
                "material",
                "material-icons-extended",
            ).forEach { alias ->
                add("implementation", libs.findLibrary(alias).get())
            }
            add("testImplementation", libs.findLibrary("junit").get())
            add("androidTestImplementation", libs.findLibrary("androidx-test-ext-junit").get())
            add("androidTestImplementation", libs.findLibrary("androidx-espresso-core").get())
            add("androidTestImplementation", platform(bom))
            add("androidTestImplementation", libs.findLibrary("androidx-compose-ui-test-junit4").get())
            add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
            add("debugImplementation", libs.findLibrary("androidx-compose-ui-test-manifest").get())
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
            optIn.add("kotlin.time.ExperimentalTime")
            optIn.add("androidx.compose.material3.ExperimentalMaterial3Api")
        }
    }
}