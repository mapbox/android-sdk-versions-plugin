package com.mapbox.android.sdk.versions

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

/**
 * Defines a task to delete the created files
 */
class SDKVersionCleanUpTask extends DefaultTask {

    /**
     * Output directory set to this task.
     */
    @OutputDirectory
    File outputDir

    @TaskAction
    void action() {
        if (outputDir != null && outputDir.exists()) {
            outputDir.deleteDir()
        }
    }

}
