package com.mapbox.android.sdk.versions

import org.junit.Test
import static org.junit.Assert.*

class PersistVersionInfoTest {

    @Test
    public void validateVersions() {

        // Test cases from https://regex101.com/r/vkijKf/1/

        //Valid Semantic versions
        assertTrue(PersistSDKVersionInfo.validateVersion("0.0.4"));
        assertTrue(PersistSDKVersionInfo.validateVersion("1.2.3"))
        assertTrue(PersistSDKVersionInfo.validateVersion("10.20.30"))
        assertTrue(PersistSDKVersionInfo.validateVersion("1.1.2-prerelease+meta"))
        assertTrue(PersistSDKVersionInfo.validateVersion("1.1.2+meta"))
        assertTrue(PersistSDKVersionInfo.validateVersion("1.1.2+meta-valid"))
        assertTrue(PersistSDKVersionInfo.validateVersion("1.0.0-alpha"))
        assertTrue(PersistSDKVersionInfo.validateVersion("1.0.0-beta"))
        assertTrue(PersistSDKVersionInfo.validateVersion("1.0.0-alpha.beta.1"))
        assertTrue(PersistSDKVersionInfo.validateVersion("1.0.0-alpha.1"))
        assertTrue(PersistSDKVersionInfo.validateVersion("1.0.0-alpha0.valid"))
        assertTrue(PersistSDKVersionInfo.validateVersion("1.0.0-alpha.0valid"))
        assertTrue(PersistSDKVersionInfo.validateVersion("1.0.0-alpha-a.b-c-somethinglong+build.1-aef.1-its-okay"))
        assertTrue(PersistSDKVersionInfo.validateVersion("1.0.0-rc.1+build.1"))
        assertTrue(PersistSDKVersionInfo.validateVersion("2.0.0-rc.1+build.123"))
        assertTrue(PersistSDKVersionInfo.validateVersion("1.2.3-beta"))
        assertTrue(PersistSDKVersionInfo.validateVersion("10.2.3-DEV-SNAPSHOT"))
        assertTrue(PersistSDKVersionInfo.validateVersion("1.2.3-SNAPSHOT-123"))
        assertTrue(PersistSDKVersionInfo.validateVersion("2.0.0"))
        assertTrue(PersistSDKVersionInfo.validateVersion("2.0.0+build.1848"))
        assertTrue(PersistSDKVersionInfo.validateVersion("2.0.1-alpha.1227"))
        assertTrue(PersistSDKVersionInfo.validateVersion("1.0.0-alpha+beta"))
        assertTrue(PersistSDKVersionInfo.validateVersion("1.2.3----RC-SNAPSHOT.12.9.1--.12+788"))
        assertTrue(PersistSDKVersionInfo.validateVersion("1.2.3----R-S.12.9.1--.12+meta"))
        assertTrue(PersistSDKVersionInfo.validateVersion("1.2.3----RC-SNAPSHOT.12.9.1--.12"))
        assertTrue(PersistSDKVersionInfo.validateVersion("1.0.0+0.build.1-rc.10000aaa-kk-0.1"))
        assertTrue(PersistSDKVersionInfo.validateVersion("99999999999999999999999.999999999999999999.99999999999999999"))
        assertTrue(PersistSDKVersionInfo.validateVersion("1.0.0-0A.is.legal"))

        // Invalid Semantic Versions
        assertFalse(PersistSDKVersionInfo.validateVersion("1"))
        assertFalse(PersistSDKVersionInfo.validateVersion("1.2"))
        assertFalse(PersistSDKVersionInfo.validateVersion("1.2.3-0123"))
        assertFalse(PersistSDKVersionInfo.validateVersion("1.2.3-0123.0123"))
        assertFalse(PersistSDKVersionInfo.validateVersion("1.1.2+.123"))
        assertFalse(PersistSDKVersionInfo.validateVersion("+invalid"))
        assertFalse(PersistSDKVersionInfo.validateVersion("-invalid"))
        assertFalse(PersistSDKVersionInfo.validateVersion("-invalid+invalid"))
        assertFalse(PersistSDKVersionInfo.validateVersion("-invalid.01"))
        assertFalse(PersistSDKVersionInfo.validateVersion("alpha"))
        assertFalse(PersistSDKVersionInfo.validateVersion("alpha.beta"))
        assertFalse(PersistSDKVersionInfo.validateVersion("alpha.beta.1"))
        assertFalse(PersistSDKVersionInfo.validateVersion("alpha.1"))
        assertFalse(PersistSDKVersionInfo.validateVersion("alpha+beta"))
        assertFalse(PersistSDKVersionInfo.validateVersion("aalpha_beta"))
        assertFalse(PersistSDKVersionInfo.validateVersion("alpha."))
        assertFalse(PersistSDKVersionInfo.validateVersion("alpha.."))
        assertFalse(PersistSDKVersionInfo.validateVersion("beta"))
        assertFalse(PersistSDKVersionInfo.validateVersion("aalpha_beta"))
        assertFalse(PersistSDKVersionInfo.validateVersion("1.0.0-alpha_beta"))
        assertFalse(PersistSDKVersionInfo.validateVersion("-alpha."))
        assertFalse(PersistSDKVersionInfo.validateVersion("1.0.0-alpha.."))
        assertFalse(PersistSDKVersionInfo.validateVersion("1.0.0-alpha..1"))
        assertFalse(PersistSDKVersionInfo.validateVersion("1.0.0-alpha...1"))
        assertFalse(PersistSDKVersionInfo.validateVersion("1.0.0-alpha....1"))
        assertFalse(PersistSDKVersionInfo.validateVersion("1.0.0-alpha.....1"))
        assertFalse(PersistSDKVersionInfo.validateVersion("1.0.0-alpha......1"))
        assertFalse(PersistSDKVersionInfo.validateVersion("1.0.0-alpha.......1"))
        assertFalse(PersistSDKVersionInfo.validateVersion("01.1.1"))
        assertFalse(PersistSDKVersionInfo.validateVersion("1.01.1"))
        assertFalse(PersistSDKVersionInfo.validateVersion("1.1.01"))
        assertFalse(PersistSDKVersionInfo.validateVersion("1.2"))
        assertFalse(PersistSDKVersionInfo.validateVersion("1.2.3.DEV"))
        assertFalse(PersistSDKVersionInfo.validateVersion("1.2-SNAPSHOT"))
        assertFalse(PersistSDKVersionInfo.validateVersion("1.2.31.2.3----RC-SNAPSHOT.12.09.1--..12+788"))
        assertFalse(PersistSDKVersionInfo.validateVersion("1.2-RC-SNAPSHOT"))
        assertFalse(PersistSDKVersionInfo.validateVersion("-1.0.3-gamma+b7718"))
        assertFalse(PersistSDKVersionInfo.validateVersion("9.8.7+meta+meta"))
        assertFalse(PersistSDKVersionInfo.validateVersion("9.8.7-whatever+meta+meta"))
        assertFalse(PersistSDKVersionInfo.validateVersion("SNAPSHOT.12.09.1--------------------------------..12"))
    }

}
