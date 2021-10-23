buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependency.Gradle.gradle)
        classpath(Dependency.Gradle.kotlin)
        classpath(Dependency.Hilt.plugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
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