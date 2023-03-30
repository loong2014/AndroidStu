// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
//        google()
//        maven { setUrl("https://oss.sonatype.org/content/repositories/snapshots") }
        maven { setUrl("https://maven.aliyun.com/repository/public") }
        maven { setUrl("https://maven.aliyun.com/repository/google") }
        maven { setUrl("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { setUrl("https://jitpack.io") }
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:${Version.ClassPathVersion.gradleVersion}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.ClassPathVersion.kotlinVersion}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Version.ClassPathVersion.hiltVersion}")

    }
}

allprojects {
    repositories {
//        google()
//        maven { setUrl("https://oss.sonatype.org/content/repositories/snapshots") }
        maven { setUrl("https://maven.aliyun.com/repository/public") }
        maven { setUrl("https://maven.aliyun.com/repository/google") }
        maven { setUrl("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { setUrl("https://jitpack.io") }
        mavenCentral()
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}
