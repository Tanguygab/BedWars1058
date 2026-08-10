plugins {
    id("buildlogic.java-conventions")
}

dependencies {
    compileOnly(project(":bedwars-api"))
    compileOnly(project(":versionsupport-common"))
    compileOnly(libs.spigot) {
        version { require(libs.versions.spigot.v1193.get()) }
    }
    compileOnly(libs.mojang.datafixerupper)
    compileOnly(libs.spigot.api)
}

description = "versionsupport_v1_19_R2"
