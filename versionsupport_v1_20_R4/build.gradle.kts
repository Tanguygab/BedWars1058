plugins {
    id("buildlogic.java-conventions")
}

dependencies {
    compileOnly(project(":versionsupport_v1_20_R3"))
    compileOnly(project(":bedwars-api"))
    compileOnly(project(":versionsupport-common"))
    compileOnly(libs.spigot) {
        version { require(libs.versions.spigot.v1204.get()) }
    }
    compileOnly(libs.spigot.api)
}

description = "versionsupport_v1_20_R4"
