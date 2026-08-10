plugins {
    id("buildlogic.java-conventions")
}

dependencies {
    compileOnly(libs.spigot.api)
    api(libs.sidebar.base)
}

description = "bedwars-api"
