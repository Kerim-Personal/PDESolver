plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
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
    // buildFeatures bloğunu XML tabanlı sisteme göre düzenliyoruz.
    buildFeatures {
        // Compose'u kullanmadığımız için 'false' yapıyoruz.
        compose = false
        // View Binding'i kullandığımız için 'true' yapıyoruz.
        viewBinding = true
    }
}

dependencies {
    // --- GEREKLİ TEMEL KÜTÜPHANELER ---
    implementation(libs.androidx.core.ktx)
    // AppCompatActivity için bu kütüphane ZORUNLUDUR. Hataların çoğu buradan kaynaklanıyor.
    implementation("androidx.appcompat:appcompat:1.7.0")
    // Materyal Tasarım bileşenleri (Button, EditText vb. için)
    implementation("com.google.android.material:material:1.12.0")
    // Coroutine'leri (launch, withContext vb.) kullanmak için.
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")


    // --- SAYISAL HESAPLAMA KÜTÜPHANESİ ---
    // PDE çözümü için gerekli olan matris işlemleri kütüphanesi.
    implementation("org.ejml:ejml-all:0.44.0")


    // --- TEST KÜTÜPHANELERİ ---
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}