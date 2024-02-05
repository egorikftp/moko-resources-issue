plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.moko.resources)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true

            export(libs.moko.resources)
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.moko.resources)
        }
    }
}

android {
    namespace = "com.egoriku.mokoresourcesissue"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

multiplatformResources {
    resourcesPackage = "com.egoriku.mokoresourcesissue"
}
