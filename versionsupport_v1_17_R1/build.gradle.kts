plugins {
    id("buildlogic.java-conventions")
}

dependencies {
    compileOnly(project(":bedwars-api"))
    compileOnly(project(":versionsupport-common"))
    compileOnly(libs.spigot) {
        version { require(libs.versions.spigot.v1171.get()) }
    }
}

description = "versionsupport_v1_17_R1"
