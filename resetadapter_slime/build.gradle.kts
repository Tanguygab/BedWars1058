plugins {
    id("buildlogic.java-conventions")
}

dependencies {
    compileOnly(project(":bedwars-api"))
    compileOnly(project(":resetadapter-common"))
    compileOnly(libs.spigot.api)
    compileOnly(libs.swm)
}

description = "resetadapter-slime"
