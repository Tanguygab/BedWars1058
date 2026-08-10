plugins {
    id("buildlogic.java-conventions")
}

dependencies {
    compileOnly(project(":bedwars-api"))
    compileOnly(libs.spigot.api)
    compileOnly(libs.swm)
}

description = "resetadapter-aswm"
