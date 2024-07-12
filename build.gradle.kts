// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    kotlin(libs.plugins.serialization.get().pluginId).version(libs.versions.serializationPlugin)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.ktlint).apply(true)
    alias(libs.plugins.dagger.hilt.android).apply(false)
    alias(libs.plugins.devtools).apply(false)
    alias(libs.plugins.jetbrains.kotlin.jvm).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.room).apply(false)
    jacoco
}
