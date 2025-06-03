import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

val serverClientId: String = gradleLocalProperties(rootDir, providers).getProperty("server_client_id")

android {
    namespace = "com.appgim.app"
    compileSdk = 35

    // Activa la generación de BuildConfig
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.appgim.app"
        minSdk = 34
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "SERVER_CLIENT_ID", "\"$serverClientId\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
    implementation(project(":ui"))
    implementation(project(":data"))

    // Login
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation(libs.identity.credential)
    implementation("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")

}