import com.android.build.api.variant.BuildConfigField
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName
import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.huoergai.muse"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.huoergai.muse"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        archivesName = "hello-$versionCode-$versionName-${
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"))
        }"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        externalNativeBuild {
            cmake {
                cppFlags += "-std=c++17"
            }
            ndk {
                // 'x86_64' 'arm64-v8a' "armeabi-v7a",
                abiFilters += listOf("arm64-v8a", "x86")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        // isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }

    buildFeatures {
        // viewBinding = true
        compose = true
        // to generate BuildConfig.java file
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

androidComponents {
    onVariants {
        if (it.buildType == "debug") {
            it.buildConfigFields.apply {
                put("TMDB_KEY", BuildConfigField("String", "\"${getKey()}\"", "api_key"))
                put("TMDB_TOKEN", BuildConfigField("String", "\"${getToken()}\"", "api_token"))
            }
        }
    }
}

fun getKey(): String {
    val props = Properties()
    props.load(FileInputStream(File("local.properties")))
    return props.getProperty("TMDB_KEY", "")
}

fun getToken(): String {
    val props = Properties()
    props.load(FileInputStream(File("local.properties")))
    return props.getProperty("TMDB_TOKEN", "")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    val composeBom = platform("androidx.compose:compose-bom:2023.06.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    // Override Material Design 3 library version with a pre-release version
    implementation("androidx.compose.material3:material3:1.1.1")
    // implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.material3:material3-window-size-class")

    // hilt
    implementation("com.google.dagger:hilt-android:2.46.1")
    kapt("com.google.dagger:hilt-android-compiler:2.46.1")

    // log
    implementation("com.jakewharton.timber:timber:5.0.1")

    // startup
    implementation("androidx.startup:startup-runtime:1.1.1")

    // network
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.10.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}