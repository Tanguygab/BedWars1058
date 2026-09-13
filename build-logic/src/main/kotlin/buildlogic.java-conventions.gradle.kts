plugins {
    kotlin("jvm")
    `java-library`
    `maven-publish`
    id("com.gradleup.shadow")
}

repositories {
    maven("https://repo.andrei1058.com/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven {
        url = uri("https://repo.codemc.io/repository/nms/")
        metadataSources {
            artifact()
        }
    }
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.codemc.io/repository/maven-public/")

    maven("https://repo.helpch.at/releases/") {
        name = "PAPI-repo"
    }
    maven("https://repo.alessiodp.com/releases/") {
        name = "Parties-repo"
    }
    maven("https://simonsator.de/repo/") {
        name = "PAF-repo"
    }
    maven("https://gitlab.com/api/v4/projects/6491858/packages/maven") {
        name = "VIPFeatures-repo"
    }
    maven("https://maven.citizensnpcs.co/repo") {
        name = "Citizens-repo"
    }
    maven("https://repo.glaremasters.me/repository/concuncan") {
        name = "SlimeWorldManager-repo"
    }
    maven("https://repo.titanvale.net/releases") {
        name = "FlowNBT-repo"
        content {
            excludeGroup("org.spigotmc")
        }
    }
    maven("https://repo.infernalsuite.com/repository/maven-snapshots/") {
        name = "AdvancedSlimeWorldManager-repo"
    }
}

dependencies {
    compileOnly("net.md-5:bungeecord-chat:1.8-SNAPSHOT")
}

group = "com.andrei1058.bedwars"
version = "25.2"

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["kotlin"])
    }
}

kotlin {
    jvmToolchain(25)
}