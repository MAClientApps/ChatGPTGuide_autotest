plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
    id ("kotlin-parcelize")
    id ("kotlinx-serialization")
    id ("com.google.gms.google-services")
}

android {
    namespace = "com.testdemo.sample"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.testdemo.sample"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        all {
            buildConfigField ("String", "OPEN_AI_KEY", "\"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\"")
            buildConfigField ("String", "BASE_URL", "\"https://api.openai.com/v1/\"")
        }

        release {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.9.0")
    implementation ("androidx.appcompat:appcompat:1.5.1")
    implementation ("com.google.android.material:material:1.7.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("androidx.activity:activity-ktx:1.6.1")
    implementation ("androidx.fragment:fragment-ktx:1.5.5")
    implementation ("androidx.browser:browser:1.3.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("com.google.android.gms:play-services-measurement-api:21.2.0")

    val COROUTINES_VERSION = "1.6.4"
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINES_VERSION")

    val KTOR_VERSION = "2.2.1"
    implementation ("io.ktor:ktor-client-core:$KTOR_VERSION")
    implementation ("io.ktor:ktor-client-cio:$KTOR_VERSION")
    implementation ("io.ktor:ktor-client-content-negotiation:$KTOR_VERSION")
    implementation ("io.ktor:ktor-serialization-kotlinx-json:$KTOR_VERSION")
    implementation ("io.ktor:ktor-client-logging:$KTOR_VERSION")

    implementation ("com.google.dagger:hilt-android:${Versions.HILT_ANDROID}")
    kapt ("com.google.dagger:hilt-compiler:${Versions.HILT_ANDROID}")
    kapt ("androidx.hilt:hilt-compiler:1.0.0")

    val LIFECYCLE_VERSION = "2.5.1"
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFECYCLE_VERSION")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$LIFECYCLE_VERSION")
    implementation ("androidx.lifecycle:lifecycle-common-java8:$LIFECYCLE_VERSION")

    val COIL_VERSION = "2.2.2"
    implementation ("io.coil-kt:coil:$COIL_VERSION")
    implementation ("io.coil-kt:coil-gif:$COIL_VERSION")

    implementation ("com.adjust.sdk:adjust-android:4.33.0")
    implementation ("com.android.installreferrer:installreferrer:2.2")
    implementation ("com.google.android.gms:play-services-ads-identifier:18.0.1")
    implementation (platform("com.google.firebase:firebase-bom:31.1.1"))
    implementation ("com.google.firebase:firebase-analytics")


}