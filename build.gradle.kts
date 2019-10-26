
plugins {
    id("java-gradle-plugin")
    id("org.springframework.boot") version "2.1.8.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
}

repositories {
    jcenter()
}


dependencies {
    implementation("com.google.guava:guava:28.0-jre")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(module = "spring-boot-starter-tomcat")
    }

    implementation("org.springframework.boot:spring-boot-starter-jetty")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")


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
