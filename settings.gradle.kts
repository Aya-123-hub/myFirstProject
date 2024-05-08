pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.google.com")
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TraanslationHub"
include(":app")
 