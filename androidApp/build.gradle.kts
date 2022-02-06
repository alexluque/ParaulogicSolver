plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "cat.alexgluque.paraulogicsolver.android"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "${findProperty("androidx.compose.version")}"
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":shared"))

    implementation("com.google.android.material:material:${findProperty("android.material.version")}")

    implementation("androidx.appcompat:appcompat:${findProperty("androidx.appcompat.version")}")
    implementation("androidx.compose.ui:ui:${findProperty("androidx.compose.version")}")
    implementation("androidx.compose.ui:ui-tooling:${findProperty("androidx.compose.version")}")
    implementation("androidx.compose.ui:ui-tooling-preview:${findProperty("androidx.compose.version")}")
    implementation("androidx.compose.foundation:foundation:${findProperty("androidx.compose.version")}")
    implementation("androidx.compose.foundation:foundation-layout:${findProperty("androidx.compose.version")}")
    implementation("androidx.compose.material:material:${findProperty("androidx.compose.version")}")
    implementation("androidx.compose.runtime:runtime:${findProperty("androidx.compose.version")}")
    implementation("androidx.compose.runtime:runtime-livedata:${findProperty("androidx.compose.version")}")
    implementation("androidx.activity:activity-compose:${findProperty("androidx.activity.version")}")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${findProperty("kotlin.version")}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${findProperty("kotlinx.coroutines.version")}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${findProperty("kotlinx.coroutines.version")}")

    implementation("com.squareup.sqldelight:android-driver:${findProperty("sqldelight.version")}")
}