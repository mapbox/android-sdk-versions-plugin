package com.mapbox.android.sdk.versions

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertTrue

class SDKVersionCleanUpTaskTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder()
    private static final String BASE_DIR = "src/main/assets"
    private Project project
    private SDKVersionCleanUpTask sdkVersionCleanUpTask


    @Before
    void setUp() {
        def assetsDirectory = temporaryFolder.newFolder()

        project = ProjectBuilder.builder().withProjectDir(new File(BASE_DIR)).build()
        sdkVersionCleanUpTask = project.getTasks().create("cleanUpSDKVersions", SDKVersionCleanUpTask.class);

        sdkVersionCleanUpTask.outputDir = assetsDirectory

        if (assetsDirectory.exists()) {
            assetsDirectory.deleteDir()
        }

        sdkVersionCleanUpTask.outputDir.mkdirs()

    }

    @Test
    public void testCleanUp() {

        assertTrue(sdkVersionCleanUpTask.outputDir.exists())
        assertTrue(sdkVersionCleanUpTask.outputDir.isDirectory())

        sdkVersionCleanUpTask.action()

        assertFalse(sdkVersionCleanUpTask.outputDir.exists())

    }

}
