plugins {
    `java-library`
    `maven-publish`
    id("com.gradleup.shadow")
}

repositories {
    mavenLocal()
    maven("https://repo.andrei1058.com/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven {
        url = uri("https://repo.codemc.io/repository/nms/")
        metadataSources {
            artifact()
        }
    }

    maven("https://repo.maven.apache.org/maven2/")

    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")

    maven("https://repo.codemc.io/repository/maven-public/")

    maven {
        url = uri("https://repo.codemc.io/repository/maven-releases/")
    }

    maven {
        url = uri("https://repo.codemc.io/repository/maven-snapshots/")
    }

    maven {
        url = uri("https://simonsator.de/repo/")
    }

    maven {
        url = uri("https://maven.citizensnpcs.co/repo")
    }

    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }

    maven {
        url = uri("https://repo.alessiodp.com/releases/")
    }

    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }

    maven {
        url = uri("https://repo.fusesource.com/nexus/content/repositories/releases-3rd-party/")
    }

    maven {
        url = uri("https://repo.glaremasters.me/repository/concuncan/")
    }

    maven {
        url = uri("https://repo.rapture.pw/repository/maven-snapshots/")
    }

    maven {
        url = uri("https://repo.infernalsuite.com/repository/maven-snapshots/")
    }

    maven {
        url = uri("https://repo.titanvale.net/releases")
    }
    maven("https://gitlab.com/api/v4/projects/6491858/packages/maven")
}

dependencies {
    compileOnly("net.md-5:bungeecord-chat:1.8-SNAPSHOT")
    compileOnly("commons-io:commons-io:2.13.0")
    compileOnly("org.jetbrains:annotations:24.0.1")
    compileOnly("org.projectlombok:lombok:1.18.36")
}

group = "com.andrei1058.bedwars"
version = "25.2"
java.sourceCompatibility = JavaVersion.VERSION_25

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}
