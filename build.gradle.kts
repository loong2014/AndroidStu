// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://oss.sonatype.org/content/repositories/snapshots") }
        maven { setUrl("https://jitpack.io") }
        maven { setUrl("https://maven.aliyun.com/repository/public") }
        maven { setUrl("https://maven.aliyun.com/repository/google") }
        maven { setUrl("https://maven.aliyun.com/repository/gradle-plugin") }

    }

    dependencies {
        classpath("com.android.tools.build:gradle:${Version.ClassPathVersion.gradleVersion}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.ClassPathVersion.kotlinVersion}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Version.ClassPathVersion.hiltVersion}")

    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://oss.sonatype.org/content/repositories/snapshots") }
        maven { setUrl("https://jitpack.io") }
        maven { setUrl("https://maven.aliyun.com/repository/public") }
        maven { setUrl("https://maven.aliyun.com/repository/google") }
        maven { setUrl("https://maven.aliyun.com/repository/gradle-plugin") }
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}
