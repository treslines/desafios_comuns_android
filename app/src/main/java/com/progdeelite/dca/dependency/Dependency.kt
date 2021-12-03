package com.progdeelite.dca.dependency

// +-----------------------------------------------------------------------------------------------+
// https://proandroiddev.com/better-dependencies-management-using-buildsrc-kotlin-dsl-eda31cdb81bf |
// +-----------------------------------------------------------------------------------------------+
object Versions {
    const val appMinSdk = 23
    const val appCompileSdk = 31
    const val appTargetSdk = 31

    val appVersionCode = parseGitCommitCount()
    const val appVersionName = "1.0.0" // VERSÃO QUE SEU APLICATICO TERÁ
    const val androidBuildTools = "7.0.3"
    const val androidJunit5Plugin = "1.7.0.0"
    const val androidJunit5Instrumentation = "1.2.0"
    const val androidxAnnotation = "1.2.0"
    const val androidxAppCompat = "1.4.0-beta01"
    const val androidxCamera = "1.1.0-alpha10"
    const val androidxCameraExtensions = "1.0.0-alpha30"
    const val androidxCore = "1.7.0-rc01"
    const val androidxLifecycle = "2.3.1"
    const val androidxNavigation = "2.3.5"
    const val androidxTestRunner = "1.4.1-alpha03"
    const val constraintLayout = "2.1.1"
    const val material = "1.4.0"
    const val apacheLang = "3.9"

    const val appCenter = "4.0.0"
    const val fragment = "1.3.6"
    const val junit = "5.7.0"
    const val kotlin = "1.5.31"
    const val kotlinCoroutines = "1.5.2-native-mt"
    const val kotlinxSerialization = "1.3.0"
    const val koinAndroid = "2.1.6"
    const val koinCore = "3.0.0-alpha-4"
    const val ktor = "1.6.3"
    const val mockk = "1.12.0"
    const val multiplatformSettings = "0.8.1"
    const val sonar = "3.0"
    const val timber = "5.0.1"
    const val klock = "1.12.0"
    const val splashScreen = "1.0.0-alpha01"
}

object Kotlin {
    val gradlePlugin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
    val coroutinesCore by lazy {
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    }
    val kotlinxSerialization by lazy {
        "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"
    }
    val kotlinxSerializationJson by lazy {
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}"
    }
}

object Ktor {
    val mock by lazy { "io.ktor:ktor-client-mock:${Versions.ktor}" }
    val clientLogging by lazy { "io.ktor:ktor-client-logging:${Versions.ktor}" }
}

object AppCenter {
    val crashes by lazy { "com.microsoft.appcenter:appcenter-crashes:${Versions.appCenter}" }
}

object Multiplatform {
    val klock by lazy { "com.soywiz.korlibs.klock:klock:${Versions.klock}" }
}

object Android {
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.androidxAppCompat}" }
    val annotation by lazy { "androidx.appcompat:appcompat:${Versions.androidxAnnotation}" }
    val buildTools by lazy { "com.android.tools.build:gradle:${Versions.androidBuildTools}" }
    val camera by lazy { "androidx.camera:camera-camera2:${Versions.androidxCamera}" }
    val cameraLifecycle by lazy { "androidx.camera:camera-lifecycle:${Versions.androidxCamera}" }
    val cameraExtensions by lazy { "androidx.camera:camera-extensions:${Versions.androidxCameraExtensions}" }
    val cameraView by lazy { "androidx.camera:camera-view:${Versions.androidxCameraExtensions}" }
    val core by lazy { "androidx.core:core-ktx:${Versions.androidxCore}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
    val fragment by lazy { "androidx.fragment:fragment:${Versions.fragment}" }
    val fragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Versions.fragment}" }
    val fragmentTesting by lazy { "androidx.fragment:fragment-testing:${Versions.fragment}" }
    val junit5Plugin by lazy {
        "de.mannodermaus.gradle.plugins:android-junit5:${Versions.androidJunit5Plugin}"
    }
    val junit5InstrumentationCore by lazy {
        "de.mannodermaus.junit5:android-test-core:${Versions.androidJunit5Instrumentation}"
    }
    val junit5InstrumentationRunner by lazy {
        "de.mannodermaus.junit5:android-test-runner:${Versions.androidJunit5Instrumentation}"
    }
    val lifecycle by lazy { "androidx.lifecycle:lifecycle-common-java8:${Versions.androidxLifecycle}" }
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val mockkInstrumentation by lazy { "io.mockk:mockk-android:${Versions.mockk}" }
    val navigationFragment by lazy {
        "androidx.navigation:navigation-fragment-ktx:${Versions.androidxNavigation}"
    }
    val navigationUi by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.androidxNavigation}" }
    val testRunner by lazy { "androidx.test:runner:${Versions.androidxTestRunner}" }
    val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }
    val apacheLang by lazy { "org.apache.commons:commons-lang3:${Versions.apacheLang}" }
    val splashscreen by lazy { "androidx.core:core-splashscreen:${Versions.splashScreen}" }
}

object Koin {
    val android by lazy { "org.koin:koin-android:${Versions.koinAndroid}" }
    val viewModel by lazy { "org.koin:koin-android-viewmodel:${Versions.koinAndroid}" }
    val core by lazy { "org.koin:koin-core:${Versions.koinCore}" }
    val test by lazy { "org.koin:koin-test:${Versions.koinCore}" }
}

object Persistence {
    val multiplatformSettings by lazy {
        "com.russhwolf:multiplatform-settings:${Versions.multiplatformSettings}"
    }
}

object Testing {
    val coroutines by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutines}" }
    val junitJupiterApi by lazy { "org.junit.jupiter:junit-jupiter-api:${Versions.junit}" }
    val junitJupiterEngine by lazy { "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}" }
    val junitJupiterParams by lazy { "org.junit.jupiter:junit-jupiter-params:${Versions.junit}" }
    val mockk by lazy { "io.mockk:mockk:${Versions.mockk}" }
}