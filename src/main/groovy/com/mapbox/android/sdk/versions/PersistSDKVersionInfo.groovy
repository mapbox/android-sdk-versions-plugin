package com.mapbox.android.sdk.versions

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.impldep.com.google.common.annotations.VisibleForTesting

import java.util.regex.Pattern

/**
 * This plugin class persists the SDK version information into a file named after the applicationId in assets folder.
 * For every library variant in the project, this plugin reacts to resources generation
 * part of build process and executes {@link SaveSDKVersionInfoTask}
 *
 */
class PersistSDKVersionInfo implements Plugin<Project> {

    private static final String SDK_VERSIONS_DIR = "/sdk_versions"
    private static final String SEM_VER_REGEX = "^(0|[1-9]\\d*)\\.(0|[1-9]\\d*)\\.(0|[1-9]\\d*)(?:-((?:0|[1-9]" +
            "\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\\.(?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*))*))" +
            "?(?:\\+([0-9a-zA-Z-]+(?:\\.[0-9a-zA-Z-]+)*))?\$"
    private static final String ASSETS_DIR = "/src/main/assets"

    @Override
    void apply(Project project) {

        def saveSDKVersionTask = project.tasks.create("saveSDKVersion", SaveSDKVersionInfoTask)
        def assetsDirectory = new File(project.projectDir, ASSETS_DIR)
        def sdkVersionsDir = new File(assetsDirectory, SDK_VERSIONS_DIR)
        saveSDKVersionTask.outputDir = sdkVersionsDir
        saveSDKVersionTask.sdkName = project.name

        project.afterEvaluate {
            project.android.libraryVariants.all { variant ->

                def outputFile = new File(sdkVersionsDir, variant.applicationId)
                saveSDKVersionTask.outputFile = outputFile
                saveSDKVersionTask.sdkVersion = project.version
                saveSDKVersionTask.sdkVersionCode = variant.generateBuildConfig.versionCode

                if (!validateVersion(saveSDKVersionTask.sdkVersion)) {
                    throw new IllegalStateException("Version $saveSDKVersionTask.sdkVersion invalid" +
                            " for $project.name .Should match the standard https://semver.org")
                }

                if (variant.respondsTo("registerGeneratedResFolders")) {
                    saveSDKVersionTask.ext.generatedResFolders = project
                            .files(sdkVersionsDir)
                            .builtBy(saveSDKVersionTask)
                    variant.registerGeneratedResFolders(saveSDKVersionTask.generatedResFolders)
                    if (variant.hasProperty("mergeResourcesProvider")) {
                        variant.mergeResourcesProvider.configure { dependsOn(saveSDKVersionTask) }
                    } else {
                        //noinspection GrDeprecatedAPIUsage
                        variant.mergeResources.dependsOn(saveSDKVersionTask)
                    }
                } else {
                    //noinspection GrDeprecatedAPIUsage
                    variant.registerResGeneratingTask(saveSDKVersionTask, sdkVersionsDir)
                }
            }
        }

        def cleanupTask = project.tasks.create("cleanSDKVersions", SDKVersionCleanUpTask)
        cleanupTask.outputDir = sdkVersionsDir
        project.tasks.findByName("clean").dependsOn(cleanupTask)
    }

    /**
     * Validates the version string according to <a href="https://semver.org/">https://semver.org/</a>
     * <p>
     * @param version version string
     * @return true if version conforms to regex pattern {@value #SEM_VER_REGEX}
     */
    @VisibleForTesting
    static boolean validateVersion(String version) {
        return Pattern.matches(SEM_VER_REGEX, version as CharSequence)
    }
}
