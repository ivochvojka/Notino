//val kotlin_version: String by extra
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation

        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
    }

}

dependencies {
    // libs if provided
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // AndroidX
    implementation(Dependency.AndroidX.constraintLayout)
    implementation(Dependency.AndroidX.appCompat)
    implementation(Dependency.AndroidX.fragment)
    implementation(Dependency.AndroidX.Lifecycle.runtime)
    implementation(Dependency.material)

    // Hilt
    implementation(Dependency.Hilt.hilt)
    kapt(Dependency.Hilt.compiler)

    // Retrofit and OkHttp
    implementation(Dependency.OkHttp.logger)
    implementation(Dependency.Retrofit.core)
    implementation(Dependency.Retrofit.gson)

    // Room
    implementation(Dependency.Room.runtime)
    kapt(Dependency.Room.compiler)
    implementation(Dependency.Room.ktx)

    // Other libs
    implementation(Dependency.glide)

    // Tests
    androidTestImplementation(Dependency.AndroidX.Test.junitExt)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

repositories {
    mavenCentral()
}