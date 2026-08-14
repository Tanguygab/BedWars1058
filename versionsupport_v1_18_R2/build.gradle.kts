plugins {
    id("buildlogic.java-conventions")
}

dependencies {
    compileOnly(project(":bedwars-api"))
    compileOnly(project(":versionsupport-common"))
    compileOnly(libs.spigot) {
        version { require(libs.versions.spigot.v1182.get()) }
    }
    compileOnly(libs.mojang.brigadier)
    compileOnly(libs.spigot.api)
    compileOnly("io.netty:netty-all:5.0.0.Alpha2") // IDK why this is needed but eh
}

description = "versionsupport_v1_18_R2"
