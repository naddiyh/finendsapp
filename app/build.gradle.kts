plugins {
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
//    signingConfigs {
//        getByName("debug") {
//            storeFile = file("C:\\Users\\ASUS\\Documents\\KeyStoreFinends\\keystore.jks")
//            storePassword = "shalatberjaya"
//            keyAlias = "keyfinends"
//            keyPassword = "shalatberjaya"
//        }
//    }
    namespace = "com.unos.finends"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.unos.finends"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        signingConfig = signingConfigs.getByName("debug")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation ("io.coil-kt:coil-compose:2.1.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.volley)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.play.services.auth)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    implementation(libs.androidx.navigation.compose)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
//    implementation(libs.firebase.analytics)
//    implementation(libs.firebase.bom)
    implementation (libs.google.firebase.auth.ktx)

    //Credential
    implementation (platform("com.google.firebase:firebase-bom:33.3.0"))
    implementation("com.google.android.gms:play-services-auth:21.2.0")
    implementation("androidx.credentials:credentials:1.2.2")
    implementation ("androidx.credentials:credentials-play-services-auth:1.2.2")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")
    implementation("com.google.firebase:firebase-analytics")

//    implementation ("androidx.compose.material3:material3:<latest-version>")
//    implementation ("androidx.compose.material3:material3-icons-extended:<latest-version>")

    //animation
    implementation ("androidx.navigation:navigation-compose:2.5.0")
    implementation ("androidx.compose.animation:animation:1.5.0") 

}