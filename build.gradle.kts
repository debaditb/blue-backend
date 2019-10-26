plugins {
    java
    id("org.springframework.boot") version "2.2.0.RELEASE" apply true
    id("io.spring.dependency-management") version "1.0.8.RELEASE" apply true
}

repositories {
    jcenter()
}

data class ForcedGroup(val groupPrefix: String, val module: String, val version: String) {
    fun matchesAnyModule(): Boolean {
        return module == "<ANY>"
    }
}

val forcedGroups = listOf(
        ForcedGroup(groupPrefix = "org.slf4j", module = "slf4j-api", version = "1.7.26"),
        ForcedGroup(groupPrefix = "org.apache.commons", module = "commons-lang3", version = "3.8.1"),
        ForcedGroup(groupPrefix = "org.ow2.asm", module = "asm", version = "7.0"),
        ForcedGroup(groupPrefix = "com.fasterxml.jackson.core", module = "<ANY>", version = "2.9.9")
)

configurations.all {
    resolutionStrategy {
        failOnVersionConflict()
    }

    exclude(module = "kotlin-stdlib-jdk7")

    resolutionStrategy.eachDependency {
        forcedGroups.forEach { fg ->
            if (this.requested.group.startsWith(fg.groupPrefix)) {
                if (fg.matchesAnyModule() || this.requested.module.name == fg.module) {
                    this.useVersion(fg.version)
                    logger.info("Forcing dependency version: [${fg.version}] on module: [${this.requested.module}]")
                }
            }
        }
    }
}

configurations {
    all {
        // Using log4j2 so need to exclude the others
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    }
}


dependencies {
    implementation("com.google.guava:guava:28.0-jre")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(module = "spring-boot-starter-tomcat")
    }

    implementation("org.springframework.boot:spring-boot-starter-jetty")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")

    implementation("org.springframework.boot:spring-boot-starter-json:2.2.0.RELEASE")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "junit") //Exclude junit 4
    }

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.5.2")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
}

/**
 * Need to override the spring BOM defined version of junit-jupiter otherwise:
 * java.lang.NoSuchMethodError: org.junit.platform.commons.util.ReflectionUtils.tryToLoadClass(Ljava/lang/String;)Lorg/junit/platform/commons/function/Try
 */
ext["junit-jupiter.version"] = "5.5.2"

java {
    sourceCompatibility = JavaVersion.VERSION_12
    targetCompatibility = JavaVersion.VERSION_12
}

tasks.withType<Test> {
    useJUnitPlatform()
}

