[![CircleCI](https://circleci.com/gh/mapbox/android-sdk-versions-plugin/tree/master.svg?style=svg&circle-token=b3b724f04666af2ffa3f0076d8250a64a831eafe)](https://circleci.com/gh/mapbox/android-sdk-versions-plugin/tree/master)

# Persist Mapbox SDK Versions

### About `android-sdk-versions-plugin`
This plugin persists Mapbox android SDK version information in a file 
at compile time for further access at run time. This plugin also validates the version 
to be compliant with https://semver.org/ .
This file is persisted in assets/sdk-versions folder. The persisted file is named after 
applicationId(for e.g. com.mapbox.andorid.core) and the file contents are in the following format. 
```$xslt
SDK Module Name/Version
Version Code
```

This plugin is inspired from https://github.com/google/play-services-plugins/tree/master/oss-licenses-plugin

All Mapbox android SDK modules that incorporate the Telemetry SDK and meant to be released 
as libraries(artifacts) should apply this plugin to facilitate the Telemetry SDK access 
all Mapbox library versions at run time.

### Add the Gradle plugin 
In your root-level build.gradle, make sure you are using the Maven Central Repository and 
add android-sdk-versions-plugin to your dependencies
```$xslt
   maven {
       url 'https://api.mapbox.com/downloads/v2/releases/maven'
       authentication {
           basic(BasicAuthentication)
       }
       credentials {
           username = "mapbox"
           password = project.hasProperty('SDK_REGISTRY_TOKEN') ? project.property('SDK_REGISTRY_TOKEN') : System.getenv('SDK_REGISTRY_TOKEN')
       }
   }   

  dependencies {
    // ...
        // Add this line:
        classpath 'com.mapbox.mapboxsdk:mapbox-android-sdk-versions:X.X.X'
        
    }

```

   
### Apply the Gradle plugin
In all your module (library) level build.gradle files, apply the plugin.

apply plugin: 'com.mapbox.android.sdk.versions'

## or

If all modules in your project are libraries, add this in project's root build.gradle file.
   ```$xslt
subprojects { subProject ->
    afterEvaluate {
        subProject.apply plugin: 'com.mapbox.android.sdk.versions'
    }
}
```


## Using Snapshots

If you want to test recent bug fixes or features that have not been packaged in an official 
release yet, you can use a -SNAPSHOT release of the current development version, available on Sonatype.

```$xslt

    repositories { 
        maven {
            url 'https://api.mapbox.com/downloads/v2/snapshots/maven'
            authentication {
                basic(BasicAuthentication)
            }
            credentials {
                username = "mapbox"
                password = project.hasProperty('SDK_REGISTRY_TOKEN') ? project.property('SDK_REGISTRY_TOKEN') : System.getenv('SDK_REGISTRY_TOKEN')
            }
        }
    }

  dependencies {
    // ...
        // Add this line:
        classpath 'com.mapbox.mapboxsdk:mapbox-android-sdk-versions:X.X.X-SNAPSHOT'
        
    }

```

# Note: 
A new folder ```sdk_versions``` with a new file will be generated inside assets folder if built locally. This is a build generated folder and can be safely added to .gitignore if not required to be seen inside the remote repository.

