plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    id("kotlin-kapt")
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

    implementation(project(":domain"))
}