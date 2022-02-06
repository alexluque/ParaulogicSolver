buildscript {
    val compose_version by extra("${findProperty("androidx.compose.version")}")

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${findProperty("kotlin.version")}")
        classpath("com.android.tools.build:gradle:${findProperty("android.gradle.version")}")
        classpath("com.squareup.sqldelight:gradle-plugin:${findProperty("sqldelight.version")}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}