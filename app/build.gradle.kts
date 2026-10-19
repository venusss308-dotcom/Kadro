plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.room)
    alias(libs.plugins.ksp)
}

android {
    namespace = "me.ibrahim.moviesapp.compose"
    compileSdk = 35

    defaultConfig {
        applicationId = "me.ibrahim.moviesapp.compose"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/movie/\"")
        buildConfigField("String", "POSTER_IMAGES_BASEURL", "\"https://image.tmdb.org/t/p/w300\"")
        buildConfigField("String", "BACKDROP_IMAGES_BASEURL", "\"https://image.tmdb.org/t/p/w780\"")
        buildConfigField("String", "API_KEY", "\"1afa749c795f5974533a3ad0b1111784\"")
        buildConfigField(
            "String",
            "API_TOKEN",
            "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYWZhNzQ5Yzc5NWY1OTc0NTMzYTNhZDBiMTExMTc4NCIsIm5iZiI6MTczODE2NzM2NS41MzcsInN1YiI6IjY3OWE1NDQ1NDlmYmU0NjkwMWMwZGYxYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Eiz4R8pl_mvfewcXnSIKYzdkyPmDPb5X-dUWOq6jr5c\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        kotlinCompilerExtensionVersion = "1.5.1"
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.kotlinx.serialization.json)

    // Koin for Android
    implementation(libs.koin.androidx.compose)

    //coil - image loading library
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    //ktor dependencies
    implementation(platform(libs.ktor.bom))
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.androidx.material.icons.extended)

    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
}