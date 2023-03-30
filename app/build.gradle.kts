plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Version.compileSdk
    defaultConfig {
        applicationId = Version.applicationId
        minSdk = Version.minSdk
        targetSdk = Version.targetSdk

        versionCode = Version.versionCode
        versionName = Version.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        multiDexEnabled = true

        buildConfigField("String", "PAX_BUILD_TIME", "\"${getBuildTime()}\"")
    }

    signingConfigs {
//        register("pax") {
//            storeFile = file("../platform.keystore")
//            keyAlias = "mykey"
//            keyPassword = "android"
//            storePassword = "password"
//        }
        register("pax") {
            storeFile = file("../platform.jks")
            keyAlias = "platform"
            keyPassword = "123456"
            storePassword = "123456"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("pax")
            isDebuggable = false
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("pax")
            isDebuggable = true
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    flavorDimensions.add("display")

    productFlavors {
        create("hpc") {
            dimension = "display"
        }
        create("mpc") {
            dimension = "display"
        }
    }
    sourceSets {
        sourceSets.all {
            if (name.contains("hpc")) {
                manifest.srcFile("src/main/hpc/AndroidManifest.xml")
            } else if (name.contains("mpc")) {
                manifest.srcFile("src/main/mpc/AndroidManifest.xml")
            }
        }
    }

    applicationVariants.all {
        outputs.all {
            (this as? com.android.build.gradle.internal.api.ApkVariantOutputImpl)?.outputFileName =
                buildOutputApkName("PaxLauncher", name)
        }
    }

    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
//        jvmTarget = "11"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))

    implementation(Deps.coreKtx)
    implementation(Deps.appcompat)
    implementation(Deps.activityKtx)
    implementation(Deps.fragmentKtx)

    // lifecycle
    implementation(Deps.lifecycleLiveDataKtx)
    implementation(Deps.lifecycleViewModelKtx)
    implementation(Deps.lifecycleViewModelSavedstate)
    implementation(Deps.lifecycleCommon)
    implementation(Deps.lifecycleService)
    implementation(Deps.lifecycleRuntimeKtx)
    // retrofit
    implementation(Deps.retrofit)
    implementation(Deps.retrofitConverterGson)
    implementation(Deps.okhttpLoggingInterceptor)
    // coroutines
    implementation(Deps.coroutinesCore)
    implementation(Deps.coroutinesAndroid)
    // hilt
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltAndroidCompiler)
    // glide
    implementation(Deps.glide)
    implementation(Deps.glideOkhttpIntegration)
    kapt(Deps.glideCompiler)
    kapt(Deps.glide) {
        exclude(group = "com.android.support")
    }

    // tools
    implementation(Deps.okhttp)
    implementation(Deps.mmkv)
    implementation(Deps.dataStore)
    implementation(Deps.workRuntime)
    implementation(Deps.timber)
    implementation(Deps.zxing)
    implementation(Deps.palette)

    // ui
    implementation(Deps.material)
    implementation(Deps.constraintLayout)
    implementation(Deps.recyclerView)
    implementation(Deps.viewpager2)
    implementation(Deps.flexbox)
    implementation(Deps.navigationFragment)
    implementation(Deps.navigationUi)

    // room
    implementation(Deps.roomRuntime)
    kapt(Deps.roomCompiler)
    kapt(Deps.lifecycleCommonJava8)

    // impl for iovcloud.aar
    implementation(Deps.rxjava)
    implementation(Deps.rxjavaRxandroid)
    implementation(Deps.retrofitAdapterRxjava2)

    // test
    implementation(Deps.stetho)
    implementation(Deps.stethoOkhttp)

    testApi(Deps.testJunit)
    debugApi(Deps.debugLeakCanary)

    androidTestImplementation(Deps.androidTestJunit)
    androidTestImplementation(Deps.androidTestEspresso)
}
