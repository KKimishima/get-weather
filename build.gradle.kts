import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "2.2.0.M5"
  id("io.spring.dependency-management") version "1.0.8.RELEASE"
  id("org.asciidoctor.convert") version "1.5.8"
  kotlin("jvm") version "1.3.41"
  kotlin("kapt") version "1.3.41"
//  kotlin("kapt") version "1.2.71"
  kotlin("plugin.spring") version "1.3.41"
}

group = "com.github.kkimishima"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

val developmentOnly by configurations.creating
configurations {
  runtimeClasspath {
    extendsFrom(developmentOnly)
  }
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

repositories {
  mavenCentral()
  maven { url = uri("https://repo.spring.io/milestone") }
}

val arrow_version = "0.9.0"
dependencies {
  // kotlin
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0")
  // jackson
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  // arrow
  implementation("io.arrow-kt:arrow-core-data:$arrow_version")
  implementation("io.arrow-kt:arrow-core-extensions:$arrow_version")
  implementation("io.arrow-kt:arrow-syntax:$arrow_version")
  implementation("io.arrow-kt:arrow-typeclasses:$arrow_version")
  implementation("io.arrow-kt:arrow-extras-data:$arrow_version")
  implementation("io.arrow-kt:arrow-extras-extensions:$arrow_version")
  // spring
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-web")
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
  developmentOnly("org.springframework.boot:spring-boot-devtools")

  // spring test
  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    exclude(group = "junit", module = "junit")
    exclude(group = "org.mockito", module = "mockito-core")
  }
  testImplementation("org.junit.jupiter:junit-jupiter-api")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
  testImplementation("com.ninja-squad:springmockk:1.1.2")
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "1.8"
  }
}

