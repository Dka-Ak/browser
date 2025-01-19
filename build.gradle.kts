plugins {
    kotlin("jvm") version "1.9.5"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-client-core:2.0.0")
    implementation("io.ktor:ktor-client-cio:2.0.0")
    implementation("io.ktor:ktor-client-json:2.0.0")
    implementation("io.ktor:ktor-client-serialization:2.0.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.openjfx:javafx-controls:18.0.1")
}

application {
    mainClass.set("MainKt")
}

javafx {
    version = "18.0.1"
    modules("javafx.controls", "javafx.fxml")
}
