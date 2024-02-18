plugins {
    java
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    compileOnly("org.projectlombok:lombok:1.18.28")
    implementation("org.liquibase:liquibase-core:4.26.0")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
    implementation("com.google.guava:guava:33.0.0-jre")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.springframework.data:spring-data-redis:3.2.3")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    implementation("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
