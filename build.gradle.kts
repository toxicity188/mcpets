plugins {
    id("java")
}

group = "fr.nocsy.mcpets"
version = "4.0.4"

repositories {
    mavenCentral()
    maven {
        name = "papermc-repo"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        name = "minecraft-repo"
        url = uri("https://libraries.minecraft.net/")
    }
    maven(url = "https://maven.enginehub.org/repo/")
    maven(url = "https://mvn.lumine.io/repository/maven/")
    maven(url = "https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly("io.lumine:Mythic-Dist:5.3.5")
    compileOnly("com.ticxo.modelengine:ModelEngine:R4.0.0")
    compileOnly("com.sk89q.worldedit:worldedit-bukkit:7.3.0-SNAPSHOT")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.1.0-SNAPSHOT")
    compileOnly("net.luckperms:api:5.4")
    compileOnly("me.clip:placeholderapi:2.9.2")
    compileOnly("com.mojang:authlib:5.0.47")
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
    test {
        useJUnitPlatform()
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name()
        val props = mapOf(
            "version" to version,
        )
        inputs.properties(props)
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
}

val targetJavaVersion = 17

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(targetJavaVersion)
    }
    withJavadocJar()
    withSourcesJar()
}