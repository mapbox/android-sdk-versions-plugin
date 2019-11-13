---
name: 🚀 Release
about: Android SDK Versions Plugin Release Issue
title: "\U0001F680 [Android SDK Versions Plugin] Release x.x.x"
labels: "release"
assignees:
---

**Timeline**
When is the release scheduled?

**Pre-release Testing**
- [ ] Apply Snapshot version of plugin in SDK module and build.

**Release Checklist**
- [ ] Update snapshot version `x.x.x-SNAPSHOT`
- [ ] Update `CHANGELOG.md`
- [ ] Tag `x.x.x` in GitHub
- [ ] Make GitHub Release
- [ ] Publish `x.x.x` artifact to Maven Central

**Post Release**
- [ ] Add release javadocs to appropriate spot in /android-docs `api` folder @langsmith

/cc: @mapbox/mobile-telemetry
