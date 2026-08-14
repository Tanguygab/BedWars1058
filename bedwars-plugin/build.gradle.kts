plugins {
    id("buildlogic.java-conventions")
}

dependencies {
    api(libs.paperlib)
    compileOnly(libs.spigot.api)
    api(libs.bstats)
    api(project(":bedwars-api"))
    api(project(":resetadapter-slime"))
    api(project(":resetadapter-slimepaper"))
    api(project(":resetadapter-aswm"))

    api(project(":versionsupport-common"))
    api(project(":versionsupport_1_8_R3"))
    api(project(":versionsupport_1_12_R1"))
    api(project(":versionsupport_v1_16_R3"))
    api(project(":versionsupport_v1_17_R1"))
    api(project(":versionsupport_v1_18_R2"))
    api(project(":versionsupport_v1_19_R2"))
    api(project(":versionsupport_v1_19_R3"))
    api(project(":versionsupport_v1_20_R1"))
    api(project(":versionsupport_v1_20_R2"))
    api(project(":versionsupport_v1_20_R3"))
    api(project(":versionsupport_v1_20_R4"))

    api(libs.slf4j)
    api(libs.hikaricp)

    api(libs.sidebar.base)
    api(libs.sidebar.v18.r3)
    api(libs.sidebar.v112.r1)
    api(libs.sidebar.v116.r3)
    api(libs.sidebar.v117.r1)
    api(libs.sidebar.v118.r2)
    api(libs.sidebar.v119.r2)
    api(libs.sidebar.v119.r3)
    api(libs.sidebar.v120.r1)
    api(libs.sidebar.v120.r2)
    api(libs.sidebar.v120.r3)
    api(libs.sidebar.v120.r4)

    compileOnly(libs.paf)
    compileOnly(libs.paf.dev)
    compileOnly(libs.paf.redis)
    compileOnly(libs.alessiodp.parties)

    compileOnly(libs.vault)
    compileOnly(libs.papi)
    compileOnly(libs.citizens)
    api(libs.vipfeatures)
}

description = "bedwars-plugin"

tasks.processResources {
    val props = mapOf(
        "version" to version,
        "kotlinVersion" to libs.versions.kotlin.get(),
    )
    inputs.properties(props)
    filesMatching(listOf("plugin.yml")) {
        expand(props)
    }
}