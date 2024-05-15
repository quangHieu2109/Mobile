plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.bookshop"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.bookshop"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    implementation ("com.github.mukeshsolanki.android-otpview-pinview:otpview-compose:3.1.0")
    implementation ("com.github.mukeshsolanki.android-otpview-pinview:otpview:3.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
