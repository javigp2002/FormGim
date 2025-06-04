plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.appgim.domain"
    compileSdk = 35

    defaultConfig {
        minSdk = 34
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
    implementation(libs.androidx.hilt.android)
    implementation(libs.identity.credential)
    implementation(libs.googleid)
    kapt(libs.androidx.hilt.android.compiler)

    implementation(libs.kotlinx.serialization.json)
    // Retrofit with Kotlin serialization Converter

}