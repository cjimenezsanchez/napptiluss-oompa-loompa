plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.jime.listdetail.data"
    compileSdk = Project.compileSdk

    defaultConfig {
        minSdk = Project.minSdk
        targetSdk = Project.compileSdk

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
}

dependencies {

    implementation(project(Modules.listDetailDomain))

    implementation(AndroidX.core)
    implementation(Coroutine.coroutines)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.loggingInterceptor)
    implementation(Retrofit.gson)
    implementation(Retrofit.gsonAdapter)

    implementation(Room.runtime)
    kapt(Room.compiler)
    implementation(Room.coroutines)

    implementation(Hilt.android)
    kapt(Hilt.compiler)

    testImplementation(Testing.junit)
    androidTestImplementation(Testing.androidJunit)
}