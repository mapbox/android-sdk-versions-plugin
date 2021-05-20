checkstyle:
	./gradlew checkstyle

codenarc:
	./gradlew codenarcMain
	./gradlew codenarcTest

test:
	./gradlew test

test-coverage:
	./gradlew jacocoTestReport

release:
	./gradlew assemble

javadoc:
	./gradlew javadoc

groovydoc:
	./gradlew groovydoc

publish:
	export IS_LOCAL_DEVELOPMENT=false; ./gradlew publish

publish-local:
	# This publishes to ~/.m2/repository/com/mapbox/mapboxsdk
	export IS_LOCAL_DEVELOPMENT=true; ./gradlew publishToMavenLocal

publish-sdk-registry:
	./gradlew mapboxSDKRegistryUpload