// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.play.services) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.google.kps) apply false
}