import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import xyz.jpenilla.runpaper.task.RunServerTask

plugins {
    java
    id("xyz.jpenilla.run-paper") version "1.0.6"
    id("net.minecrell.plugin-yml.bukkit") version "0.4.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.github.morinoparty"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()

    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation("cloud.commandframework", "cloud-paper", "1.6.2")
    implementation("cloud.commandframework", "cloud-annotations", "1.6.2")
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
}
bukkit {
    name = rootProject.name
    version = project.version as String
    main = "${project.group}.kakuremi.Kakuremi"
    apiVersion = "1.18"
    authors = listOf("Unitarou")
    description = "NameTag hide plugin."
    website = "https://github.com/morinoparty/"
}

tasks {

    withType<ShadowJar> {
        this.archiveClassifier.set(null as String?)
    }

    withType<RunServerTask> {
        this.minecraftVersion("1.18.2")
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
