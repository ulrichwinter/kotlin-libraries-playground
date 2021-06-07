pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.squareup.wire") {
                useModule("com.squareup.wire:wire-gradle-plugin:3.4.0")
            }
        }
    }
}

plugins {
    id("com.gradle.enterprise") version "3.6.1"
    id("de.fayard.refreshVersions") version "0.10.0"
}


refreshVersions {
    enableBuildSrcLibs()
}

// https://dev.to/jmfayard/the-one-gradle-trick-that-supersedes-all-the-others-5bpg
gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishAlways()
        buildScanPublished {
            file("buildscan.log").appendText("${java.util.Date()} - $buildScanUri\n")
        }
    }

}

rootProject.name = "kotlin-libraries-playground"

apply(from= "gradle/detekt-android-sdk.gradle")
val hasAndroidSdk: Boolean by extra
if (hasAndroidSdk) include("android")
include("kotlin-jvm")
include("kotlin-testing")
include("kotlin-codegen")
