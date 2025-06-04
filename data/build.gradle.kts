plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
}

android {
    namespace = "com.appgim.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Hilt (DI)
    implementation(libs.androidx.hilt.android)
    kapt(libs.androidx.hilt.android.compiler)



    implementation(libs.retrofit)

    implementation(libs.kotlinx.serialization.json)
    // Retrofit with Kotlin serialization Converter

    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.okhttp)





    implementation(libs.gson)

    implementation(project(":domain"))
}