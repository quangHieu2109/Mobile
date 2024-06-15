pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        flatDir {
            dirs("libs")
        }

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        flatDir {
            dirs("libs");

        }
        mavenCentral()
        google()
        maven ("https://jitpack.io")

    }
}


rootProject.name = "BookShop"
include(":app")
 