plugins {
    id("buildlogic.java-conventions")
}

dependencies {
    compileOnly(project(":bedwars-api"))
    compileOnly(project(":versionsupport-common"))
    compileOnly(libs.spigot) {
        version { require(libs.versions.spigot.v18.get()) }
    }
}

description = "versionsupport_1_8_R3"
