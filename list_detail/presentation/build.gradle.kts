plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    kotlin("kapt")
}

android {
    namespace = "com.jime.listdetail.presentation"
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
    
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(project(Modules.listDetailDomain))
    implementation(project(Modules.coreUi))

    implementation(Hilt.android)
    kapt(Hilt.compiler)

    implementation(Navigation.runtime)
    implementation(Navigation.fragment)
    implementation(Navigation.ui)

    implementation(Coil.coil)

    implementation(AndroidX.core)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.fragment)
    implementation(AndroidX.viewModel)
    implementation(AndroidX.runtime)
    implementation(Material.material)

    testImplementation(Testing.junit)
    androidTestImplementation(Testing.androidJunit)

    testImplementation(Testing.mockito)
    androidTestImplementation(Testing.mockitoAndroid)
    testImplementation(Testing.mockitoInline)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.archCore)
}