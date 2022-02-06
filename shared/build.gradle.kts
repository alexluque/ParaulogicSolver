import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

version = "1.0"

kotlin {
    // Allows SwiftUI previews
    // https://github.com/JetBrains/kotlin-native/issues/3059
    targets.withType<KotlinNativeTarget> {
        binaries.withType<Framework> {
            isStatic = false
        }
    }

    android()
    iosX64()
    iosArm64()
    //iosSimulatorArm64() sure all ios dependencies support this target

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${findProperty("kotlinx.coroutines.version")}")
                implementation("com.doist.x:normalize:${findProperty("doist.x.normalize.version")}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation("app.cash.turbine:turbine:${findProperty("app.cash.turbine.version")}")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:android-driver:${findProperty("sqldelight.version")}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${findProperty("kotlinx.coroutines.version")}")
                implementation("androidx.lifecycle:lifecycle-extensions:${findProperty("androidx.lifecycle.extensions.version")}")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:${findProperty("junit")}")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        //val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            //iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation("com.squareup.sqldelight:native-driver:${findProperty("sqldelight.version")}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${findProperty("kotlinx.coroutines.version")}") {
                    version {
                        strictly("${findProperty("kotlinx.coroutines.version")}")
                    }
                }
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        //val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            //iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 31
    }
}

sqldelight {
    database("ParaulogicDB") {
        packageName = "cat.alexgluque.paraulogicsolver"
        version = 1
        schemaOutputDirectory = file("src/commonMain/sqldelight/cat/alexgluque/paraulogicsolver")
    }
}