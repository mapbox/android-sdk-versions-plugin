package com.mapbox.android.sdk.versions

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.internal.impldep.com.google.common.annotations.VisibleForTesting

/**
 * Task to create a file with specified name and write specified SDK version into it.
 */
class SaveSDKVersionInfoTask extends DefaultTask {

    /**
     * Output directory set to this task.
     */
    @OutputDirectory
    public File outputDir

    /**
     * Output file set to this task.
     */
    @OutputFile
    public File outputFile


    /**
     * SDK version set to this task.
     */
    public String sdkVersion


    @TaskAction
    @VisibleForTesting
    void action() {

        initOutputDirectory()
        initOutputFile()

        def fileWriter = new FileWriter(outputFile)
        fileWriter.println(sdkVersion)
        fileWriter.close()

    }

    /**
     * Creates output directory if not exists
     */
    @VisibleForTesting
    protected void initOutputDirectory() {

        if (!outputDir.exists() || !outputDir.isDirectory()) {
            outputDir.mkdirs()
        }
    }

    /**
     * Creates output file if not exists
     */
    @VisibleForTesting
    protected void initOutputFile() {

        if (!outputDir.exists() || !outputDir.isDirectory()) {
            throw new IllegalStateException("Can not create a file in non existent directory.")
        }

        if (outputFile.exists()) {
            outputFile.delete()
        }

        outputFile.createNewFile()

    }


}
