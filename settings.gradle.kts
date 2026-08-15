pluginManagement {
    includeBuild("build-logic")
}

rootProject.name = "BedWars1058"
include(":bedwars-api")
include(":bedwars-plugin")

include(":resetadapter-common")
include(":resetadapter-aswm")
include(":resetadapter-slime")
include(":resetadapter-slimepaper")

include(":versionsupport-common")
include(":versionsupport_1_8_R3")
include(":versionsupport_1_12_R1")
include(":versionsupport_v1_16_R3")
include(":versionsupport_v1_17_R1")
include(":versionsupport_v1_19_R2")
include(":versionsupport_v1_19_R3")
include(":versionsupport_v1_18_R2")
include(":versionsupport_v1_20_R1")
include(":versionsupport_v1_20_R2")
include(":versionsupport_v1_20_R3")
include(":versionsupport_v1_20_R4")

project(":resetadapter-aswm").projectDir = file("resetadapter_aswm")
project(":resetadapter-slime").projectDir = file("resetadapter_slime")
project(":resetadapter-slimepaper").projectDir = file("resetadapter_slimepaper")
project(":versionsupport-common").projectDir = file("versionsupport_common")
