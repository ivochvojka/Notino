object Dependency {

    object Gradle {
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31"
        const val gradle = "com.android.tools.build:gradle:7.0.2"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.3.1"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.1"
        const val core = "androidx.core:core-ktx:1.6.0"
        const val fragment = "androidx.fragment:fragment-ktx:1.3.0"

        object Lifecycle {
            private const val version = "2.4.0-rc01"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        }

        object Test {
            private const val version = "1.1.2"
            const val junitExt = "androidx.test.ext:junit:$version"
        }
    }

    object Hilt {
        private const val version = "2.38.1"
        const val plugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val hilt = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
    }

    object OkHttp {
        private const val version = "4.9.2"
        const val core = "com.squareup.okhttp3:okhttp:$version"
        const val logger = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val core = "com.squareup.retrofit2:retrofit:$version"
        const val gson = "com.squareup.retrofit2:converter-gson:$version"
        const val scalars = "com.squareup.retrofit2:converter-scalars:$version"
    }

    object Room {
        private const val version = "2.3.0"
        const val compiler = "androidx.room:room-compiler:$version"
        const val runtime = "androidx.room:room-runtime:$version"
        const val ktx = "androidx.room:room-ktx:$version"
        const val test = "androidx.room:room-testing:$version"
    }

    const val material = "com.google.android.material:material:1.1.0"
    const val glide = "com.github.bumptech.glide:glide:4.12.0"
}