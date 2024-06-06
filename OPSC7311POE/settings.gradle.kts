pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven ( url = "https://jitpack.io" )
    }

}


dependencyResolutionManagement {

repositories {
        google()
        mavenCentral()
    maven ( url = "https://jitpack.io" )

    }
}

rootProject.name = "OPSC7311 POE"
include(":app")
 