import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

val serverClientId: String = gradleLocalProperties(rootDir, providers).getProperty("server_client_id")
val webClientId: String = gradleLocalProperties(rootDir, providers).getProperty("web_client_id")

android {
    namespace = "com.example.formgim"
    compileSdk = 35
    defaultConfig {
        minSdk = 34
        buildConfigField("String", "SERVER_CLIENT_ID", "\"$serverClientId\"")
        buildConfigField("String", "WEB_CLIENT_ID", "\"$webClientId\"")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

    // Activa la generación de BuildConfig
    buildFeatures {
        buildConfig = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(project(":domain"))


    // Icons
    implementation(libs.androidx.material.icons.extended.android)

    // Nav
    implementation(libs.androidx.hilt.navigation.compose)
    val nav_version = "2.8.9"

    // Jetpack Compose integration
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // Views/Fragments integration
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")

    // Feature module support for Fragments
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")

    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")

    // JSON serialization library, works with the Kotlin serialization plugin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    implementation(libs.gson)

    implementation("androidx.compose.ui:ui-text-google-fonts:1.8.2")
    // Hilt (DI)
    implementation(libs.androidx.hilt.android)
    kapt(libs.androidx.hilt.android.compiler)

    // Login
    implementation(libs.identity.credential)
    implementation(libs.googleid)

    // Image loading
    implementation(libs.coil.compose)
}