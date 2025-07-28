plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "mx.edu.uttt.dashboard"
    compileSdk = 35

    defaultConfig {
        applicationId = "mx.edu.uttt.dashboard"
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
        compose = true
    }
}

dependencies {
    // Dependencias principales (usando version catalog)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))  // BOM único
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // SOLUCIÓN: Usar solo las dependencias adaptativas del grupo material3
    implementation("androidx.compose.material3:material3-adaptive:1.0.0-alpha06")

    // Navegación
    implementation("androidx.navigation:navigation-compose:2.7.7")


    // Animaciones
    implementation("androidx.compose.animation:animation:1.5.0")

    // Gráficas con Vico Compose
    implementation("com.patrykandpatrick.vico:core:1.12.0")
    implementation("com.patrykandpatrick.vico:compose:1.12.0")
    implementation("com.patrykandpatrick.vico:compose-m3:1.12.0")

    //imagenes
    implementation ("io.coil-kt:coil-compose:2.4.0")

    //para graficas

    implementation ("io.github.patryk-and-patrick:vico-core:1.4.1")
    implementation ("io.github.patryk-and-patrick:vico-compose:1.4.1")



}