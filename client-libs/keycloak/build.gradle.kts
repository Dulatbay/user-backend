import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
    java
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management")
//    id("org.graalvm.buildtools.native") version "0.9.20"
}

group = "techpark.net"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}


repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.springdoc:springdoc-openapi-ui:1.6.14")
    implementation("com.google.code.findbugs:jsr305:3.0.2")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.security:spring-security-oauth2-client:6.0.4")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.data:spring-data-commons")
    implementation("org.springframework.boot:spring-boot-starter-test")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation("io.github.openfeign.form:feign-form:3.8.0")
    implementation("io.github.openfeign.form:feign-form-spring:3.8.0")

}

tasks.withType<Test> {
    useJUnitPlatform()
}

configure<DependencyManagementExtension> {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2022.0.3")
    }
}