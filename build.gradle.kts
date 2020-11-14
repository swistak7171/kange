plugins {
    kotlin("multiplatform") version "1.4.10"
    id("application")
}

group = "pl.kamilszustak"
version = "0.1.0"

kotlin {
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }

    sourceSets {
        val nativeMain by getting {
            dependencies {
                implementation("com.github.ajalt.clikt:clikt:3.0.1")
            }
        }

        val nativeTest by getting
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}