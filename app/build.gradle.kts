// app/build.gradle.kts
// Source: app/build.gradle.kts (lines 5-29)
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.pdesolver"
    compileSdk = 35 //

    defaultConfig {
        applicationId = "com.example.pdesolver" //
        minSdk = 24 //
        targetSdk = 35 //
        versionCode = 1 //
        versionName = "1.0" //

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" //
    } //

    buildTypes {
        release {
            isMinifyEnabled = false //
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), //
                "proguard-rules.pro" //
            )
        }
    } //

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11 //
        targetCompatibility = JavaVersion.VERSION_11 //
    }
    kotlinOptions {
        jvmTarget = "11" //
    }
    buildFeatures {
        viewBinding = true // Bu satırın olduğundan emin olun
        compose = false
    }
}

dependencies {
    // --- GEREKLİ TEMEL KÜTÜPHANELER ---
    implementation(libs.androidx.core.ktx)
    implementation("androidx.appcompat:appcompat:1.7.0") //
    implementation("com.google.android.material:material:1.12.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

    // --- SAYISAL HESAPLAMA KÜTÜPHANESİ ---
    // EJML kütüphanesi için gerekliyse:
    // implementation("org.ejml:ejml-all:0.44.0") // Mevcut ama kullanılmıyor gibi görünüyor, şimdilik dursun

    // --- TEST KÜTÜPHANELERİ ---
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}