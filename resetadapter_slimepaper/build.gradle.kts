plugins {
    id("buildlogic.java-conventions")
}

dependencies {
    compileOnly(project(":bedwars-api"))
    compileOnly(libs.spigot.api)
    compileOnly(libs.aswm)
}

description = "resetadapter-slimepaper"
