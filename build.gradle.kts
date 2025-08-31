plugins {
    kotlin("jvm") version "2.2.10"
    kotlin("plugin.serialization") version "2.0.20"
}

group = "com.ninjashadowboy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")

    // JSON serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

    // For TUI
    implementation("org.jline:jline:3.26.2")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}