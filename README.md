[![CircleCI](https://circleci.com/gh/mapbox/mapbox-events-android.svg?style=svg&circle-token=b206c88b942901329c5d8632a9e5d1b8cd501a61)](https://circleci.com/gh/mapbox/mapbox-events-android)
[![codecov](https://codecov.io/gh/mapbox/android-sdk-versions-plugin/branch/master/graph/badge.svg)](https://codecov.io/gh/mapbox/android-sdk-versions-plugin/branch/master)

# Persist Mapbox SDK Versions


### About `android-sdk-versions-plugin`
This plugin persists Mapbox android SDK version(for every library module) in a file 
at compile time for further access at run time. This plugin also validates the version 
to be compliant with https://semver.org/ .
The persisted file is named after applicationId(for e.g. com.mapbox.andorid.core) and the file content
 is library(module) version.

This plugin is inspired from https://github.com/google/play-services-plugins/tree/master/oss-licenses-plugin

All android SDK library modules at Mapbox should apply this plugin to facilitate the Telemetry SDK 
access all Mapbox SDK versions at run time.

### Add the Gradle plugin 
In your root-level build.gradle, make sure you are using the Maven Central Repository and 
add android-sdk-versions-plugin to your dependencies
```$xslt

  dependencies {
    // ...
        // Add this line:
        classpath 'com.mapbox.mapboxsdk:mapbox-android-sdk-versions:X.X.X'
        
       }

```

   
### Apply the Gradle plugin
In all your module (library) level build.gradle files, apply the plugin.

apply plugin: 'com.mapbox.android.sdk.versions'

###or

If all modules in your project are libraries, add this in project's root build.gradle file.
   ```$xslt
subprojects { subProject ->
    afterEvaluate {
        subProject.apply plugin: 'com.mapbox.android.sdk.versions'
    }
}
```


##Using Snapshots

If you want to test recent bug fixes or features that have not been packaged in an official 
release yet, you can use a -SNAPSHOT release of the current development version, available on Sonatype.

```$xslt

  dependencies {
    // ...
        // Add this line:
        classpath 'com.mapbox.mapboxsdk:mapbox-android-sdk-versions:X.X.X'
        
       }

```

