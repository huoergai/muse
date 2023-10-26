import com.android.build.api.variant.BuildConfigField
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName
import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    compileSdk = 34
    ndkVersion = "26.1.10909125"
    namespace = "com.huoergai.muse"
    defaultConfig {
        applicationId = "com.huoergai.muse"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        archivesName = "Muse-$versionCode-$versionName-${
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"))
        }"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        externalNativeBuild {
            cmake {
                cppFlags += "-std=c++20"
            }
            ndk {
                // "x86" 'x86_64' 'arm64-v8a' "armeabi-v7a",
                //noinspection ChromeOsAbiSupport
                abiFilters += listOf("arm64-v8a")
            }
        }
    }

    buildTypes {
        debug {
            isShrinkResources = false
            isMinifyEnabled = false
        }
        release {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }

    buildFeatures {
        viewBinding = true
        // to generate BuildConfig.java file
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
        jniLibs {
            useLegacyPackaging = true
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

// hilt: Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.activity:activity-ktx:1.8.0")
    implementation("androidx.fragment:fragment-ktx:1.6.1")
    debugImplementation("androidx.fragment:fragment-testing:1.6.1")

    // lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // ui
    val appcompatVersion = "1.6.1"
    implementation("androidx.appcompat:appcompat:$appcompatVersion")
    implementation("androidx.appcompat:appcompat-resources:$appcompatVersion")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")

    implementation("androidx.palette:palette-ktx:1.0.0")

    // expandable TextView
    implementation("com.ms-square:expandableTextView:0.1.4")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // hilt
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")

    // log
    implementation("com.jakewharton.timber:timber:5.0.1")

    // startup
    implementation("androidx.startup:startup-runtime:1.1.1")

    // coil
    implementation("io.coil-kt:coil:2.4.0")

    // network
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.11.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
    testImplementation("com.squareup.okhttp3:mockwebserver")

    // test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}