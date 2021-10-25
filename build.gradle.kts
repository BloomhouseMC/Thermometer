plugins {
    id("fabric-loom") version "0.10.27"
    id("maven-publish")
    id("io.github.juuxel.loom-quiltflower") version "1.3.0"
}

version = "${project.property("mod_version")}"
group = "${project.property("maven_group")}"

repositories {
    // Add repositories to retrieve artifacts from in here.
    // You should only use this when depending on other mods because
    // Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
    // See https://docs.gradle.org/current/userguide/declaring_repositories.html
    // for more information about repositories.
}

dependencies {
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:${project.property("minecraft_version")}")
    mappings("net.fabricmc:yarn:${project.property("yarn_mappings")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${project.property("loader_version")}")

    // Fabric API. This is technically optional, but you probably want it anyway.
    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.property("fabric_version")}")

    // PSA: Some older mods, compiled on Loom 0.2.1, might have outdated Maven POMs.
    // You may need to force-disable transitiveness on them.
}

val targetJavaVersion = 17

tasks {
    processResources {
        inputs.property("version", project.version)
        filteringCharset = "UTF-8"

        filesMatching("fabric.mod.json") {
            expand("version" to project.version)
        }
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
        if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible()) {
            options.release.set(targetJavaVersion)
        }
    }

    java {
        val javaVersion =JavaVersion.toVersion(targetJavaVersion)
        if (JavaVersion.current() < javaVersion) {
            toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
        }
        base.archivesName.set("${project.property("archives_base_name")}")
        withSourcesJar()
    }


    jar {
        from("LICENSE") {
            rename {
                "${it}_${project.base.archivesName}"
            }
        }
    }
}

publishing {

    // See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
    publications {
        register("mavenJava", MavenPublication::class) {
            // add all the jars that should be included when publishing to maven
//            artifact(remapJar.get()) {
//                builtBy(remapJar)
//            }
//            artifact(sourcesJar.get()) {
//                builtBy(remapSourcesJar)
//            }
        }
    }
    repositories {
        // Add repositories to publish to here.
        // Notice: This block does NOT have the same function as the block in the top level.
        // The repositories here will be used for publishing your artifact, not for
        // retrieving dependencies.
    }
}
