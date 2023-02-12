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
    implementation(Kotlin.coroutines)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.loggingInterceptor)
    implementation(Retrofit.gson)
    implementation(Retrofit.gsonAdapter)

    implementation(Hilt.android)
    kapt(Hilt.compiler)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}