plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.jime.napptilusoompaloompa"
    compileSdk = Project.compileSdk

    defaultConfig {
        applicationId = "com.jime.napptilusoompaloompa"
        minSdk = Project.minSdk
        targetSdk = Project.compileSdk
        versionCode = Project.versionCode
        versionName = Project.versionName

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

    implementation(project(Modules.listDetailPresentation))
    implementation(project(Modules.listDetailDomain))
    implementation(project(Modules.listDetailData))

    implementation(Hilt.android)
    kapt(Hilt.compiler)

    implementation(AndroidX.core)
    implementation(AndroidX.appCompat)
    implementation(Material.material)

}