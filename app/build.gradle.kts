// kerim-personal/pdesolver/PDESolver-3adbc54dfbef6d0827a150e07deabfb1c57b3153/app/build.gradle.kts
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // alias(libs.plugins.kotlin.compose) // Bu satır kaldırıldı
}

android {
    namespace = "com.example.pdesolver"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.pdesolver"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
    buildFeatures {
        compose = false // Compose'u kullanmadığımız için 'false' olarak kalmalı
        viewBinding = true
    }
}

dependencies {
    // --- GEREKLİ TEMEL KÜTÜPHANELER ---
    implementation(libs.androidx.core.ktx)
    implementation("androidx.appcompat:appcompat:1.7.0") // AppCompatActivity için bu kütüphane ZORUNLUDUR.
    implementation("com.google.android.material:material:1.12.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

    // --- SAYISAL HESAPLAMA KÜTÜPHANESİ ---
    implementation("org.ejml:ejml-all:0.44.0")

    // --- TEST KÜTÜPHANELERİ ---
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}