plugins {
    id("buildlogic.java-conventions")
}

dependencies {
    compileOnly(project(":bedwars-api"))
    compileOnly(project(":versionsupport-common"))
    compileOnly(libs.spigot) {
        version { require(libs.versions.spigot.v1194.get()) }
    }
    compileOnly(libs.spigot.api)
}

description = "versionsupport_v1_19_R3"
