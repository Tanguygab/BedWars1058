plugins {
    id("buildlogic.java-conventions")
}

dependencies {
    compileOnly(project(":bedwars-api"))
    compileOnly(libs.spigot) {
        version { require(libs.versions.spigot.v1204.get()) }
    }
    compileOnly(libs.spigot.api)
    compileOnly(libs.mojang.datafixerupper)
}

description = "versionsupport-common"
