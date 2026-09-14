plugins {
    id("buildlogic.java-conventions")
}

dependencies {
    api(libs.paperlib)
    compileOnly(libs.spigot.api)
    api(libs.sidebar.base)
}

description = "bedwars-api"
