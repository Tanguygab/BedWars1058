plugins {
    id("buildlogic.java-conventions")
}

dependencies {
    api(libs.flow.nbt)
    compileOnly(project(":bedwars-api"))
    compileOnly(libs.spigot.api)
    compileOnly(libs.aswm)
}

description = "resetadapter-slimepaper"
