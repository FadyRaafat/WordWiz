plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    alias(libs.plugins.ktlint)
    id(libs.plugins.dagger.hilt.android.get().pluginId)
    id(libs.plugins.devtools.get().pluginId)
    id(libs.plugins.room.get().pluginId)
    kotlin(libs.plugins.serialization.get().pluginId)
}

android {
    namespace = "com.fady.wordwiz"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fady.wordwiz"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.dictionaryapi.dev/api/v2/entries/en/\"")

        }
        release {
            buildConfigField("String", "BASE_URL", "\"https://api.dictionaryapi.dev/api/v2/entries/en/\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
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
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }

}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.bundles.compose)
    // Temporarily use this version to fix google's navigation animation transition bug
    implementation(libs.landscapist.coil)
    implementation(libs.androidx.material.icons.extended.android)
    implementation(libs.material)
    // serialization
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.hilt)
    implementation(libs.hilt.android.gradle.plugin)
    implementation(libs.androidx.navigation.compose)

    // bottom navigation bar
    implementation(libs.compose.ui)
    implementation("androidx.compose.material:material:1.6.1")
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.junit.ktx)
    implementation(libs.androidx.runner)
    ksp(libs.hilt.compiler)

    // testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation(libs.splashscreen)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    testImplementation(libs.truth.truth)
    androidTestImplementation(libs.truth.truth)
    testImplementation(libs.mockito)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2")
    // paging
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Room
    implementation(libs.bundles.room)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)
    testImplementation("androidx.room:room-testing:2.6.1")
    androidTestImplementation("androidx.room:room-testing:2.6.1")

}