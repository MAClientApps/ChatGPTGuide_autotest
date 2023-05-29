// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id ("com.android.application") version "7.3.1" apply false
    id ("com.google.gms.google-services") version "4.3.13" apply false
    id ("org.jetbrains.kotlin.android") version Versions.KOTLIN apply false
    id ("com.google.dagger.hilt.android") version Versions.HILT_ANDROID apply false
    id ("org.jetbrains.kotlin.plugin.serialization") version Versions.KOTLIN apply false
}