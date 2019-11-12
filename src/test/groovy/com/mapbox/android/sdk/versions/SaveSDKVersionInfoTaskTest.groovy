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
    private static final def SDK_VERSION = "1.0.0"
    private static final String FILE_NAME = "testVersion"
    private static final String PROJECT_NAME = "test"
    private static final int VERSION_CODE = 1

    @Before
    void setUp() {
        File assetsDirectory = temporaryFolder.newFolder()
        File versionFile = new File(assetsDirectory, FILE_NAME)

        project = ProjectBuilder.builder().withName(PROJECT_NAME).withProjectDir(new File(BASE_DIR)).build()
        saveSDKVersionInfoTask = project.getTasks().create("saveSDKVersions", SaveSDKVersionInfoTask.class)
        saveSDKVersionInfoTask.outputDir = assetsDirectory
        saveSDKVersionInfoTask.outputFile = versionFile
        saveSDKVersionInfoTask.sdkVersion = SDK_VERSION
        saveSDKVersionInfoTask.sdkName = project.name
        saveSDKVersionInfoTask.sdkVersionCode = VERSION_CODE

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
        String firstLine = reader.readLine()
        String secondLine = reader.readLine()
        reader.close()

        assertEquals(firstLine, String.format(Locale.US, SaveSDKVersionInfoTask.FIRST_LINE_FORMAT, PROJECT_NAME, SDK_VERSION))
        assertEquals(secondLine, String.format(Locale.US, SaveSDKVersionInfoTask.SECOND_LINE_FORMAT, VERSION_CODE))
    }

}
