plugins {
    id("buildlogic.java-conventions")
}

dependencies {
    compileOnly(project(":bedwars-api"))
    compileOnly(project(":versionsupport-common"))
    compileOnly(libs.spigot) {
        version { require(libs.versions.spigot.v1202.get()) }
    }
    compileOnly(libs.mojang.datafixerupper)
    compileOnly(libs.spigot.api)
}

description = "versionsupport_v1_20_R2"
