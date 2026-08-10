plugins {
    id("buildlogic.java-conventions")
}

dependencies {
    compileOnly(libs.spigot.api)
    api(libs.sidebar.base)
    compileOnly(libs.google.collect)
}

description = "bedwars-api"
