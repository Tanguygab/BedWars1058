plugins {
    id("buildlogic.java-conventions")
}

dependencies {
    compileOnly(project(":bedwars-api"))
    compileOnly(libs.spigot) {
        version { require(libs.versions.spigot.v1144.get()) }
    }
}

description = "versionsupport-common"
