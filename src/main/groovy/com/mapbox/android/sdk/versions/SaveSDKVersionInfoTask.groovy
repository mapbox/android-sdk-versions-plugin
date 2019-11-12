package com.mapbox.android.sdk.versions

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.internal.impldep.com.google.common.annotations.VisibleForTesting

/**
 * Task to create a file with specified name and write specified SDK Version information into it.
 */
class SaveSDKVersionInfoTask extends DefaultTask {

    static final String FIRST_LINE_FORMAT = "%s/%s"
    static final String SECOND_LINE_FORMAT = "v%s"

    /**
     * Output directory set to this task.
     */
    @OutputDirectory
    File outputDir

    /**
     * Output file set to this task.
     */
    @OutputFile
    File outputFile

    /**
     * SDK version set to this task.
     */
    String sdkVersion

    /**
     * SDK name set to this task.
     */
    String sdkName

    /**
     * SDK version code set to this task.
     */
    Integer sdkVersionCode

    @TaskAction
    @VisibleForTesting
    void action() {
        initOutputDirectory()
        initOutputFile()

        def fileWriter = new FileWriter(outputFile)
        fileWriter.println(String.format(Locale.US, FIRST_LINE_FORMAT, sdkName, sdkVersion))
        fileWriter.println(String.format(Locale.US, SECOND_LINE_FORMAT, sdkVersionCode != null ? sdkVersionCode : ""))
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
