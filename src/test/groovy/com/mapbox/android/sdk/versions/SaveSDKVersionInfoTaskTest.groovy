package com.mapbox.android.sdk.versions

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

import static org.junit.Assert.*

class SaveSDKVersionInfoTaskTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder()
    private static final BASE_DIR = "src/main/assets"
    private Project project
    private SaveSDKVersionInfoTask saveSDKVersionInfoTask
    private static final def SDK_VERSION = "1.0"
    private static final String FILE_NAME = "testVersion"

    @Before
    void setUp() {
        File assetsDirectory = temporaryFolder.newFolder()
        File versionFile = new File(assetsDirectory, FILE_NAME)

        project = ProjectBuilder.builder().withProjectDir(new File(BASE_DIR)).build()
        saveSDKVersionInfoTask = project.getTasks().create("saveSDKVersions", SaveSDKVersionInfoTask.class)
        saveSDKVersionInfoTask.outputDir = assetsDirectory
        saveSDKVersionInfoTask.outputFile = versionFile
        saveSDKVersionInfoTask.sdkVersion = SDK_VERSION

        if (assetsDirectory.exists()) {
            assetsDirectory.deleteDir()
        }
    }

    @Test
    public void testInitOutputDirectory() {
        saveSDKVersionInfoTask.initOutputDirectory()
        assertTrue(saveSDKVersionInfoTask.outputDir.exists())
    }

    @Test
    public void testInitOutputFile() {
        saveSDKVersionInfoTask.initOutputDirectory()
        saveSDKVersionInfoTask.initOutputFile()
        assertTrue(saveSDKVersionInfoTask.outputFile.exists())
    }

    @Test(expected = Exception.class)
    public void testInitOutputFileWithoutDirectory() {
        saveSDKVersionInfoTask.initOutputFile()
    }

    @Test
    public void testAction() {
        saveSDKVersionInfoTask.action()

        assertTrue(saveSDKVersionInfoTask.outputFile.exists())
        assertFalse(saveSDKVersionInfoTask.outputFile.isDirectory())
        assertEquals(saveSDKVersionInfoTask.outputFile.name, FILE_NAME)
        assertNotEquals(saveSDKVersionInfoTask.outputFile.length(), 0)

        FileReader reader = new FileReader(saveSDKVersionInfoTask.outputFile)
        String line = reader.readLine()
        reader.close()

        assertTrue(line == SDK_VERSION)
    }

}
