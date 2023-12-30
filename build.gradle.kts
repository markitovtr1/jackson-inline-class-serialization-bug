import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "3.2.1"
  id("io.spring.dependency-management") version "1.1.4"

  kotlin("jvm") version "1.9.20"
  kotlin("plugin.spring") version "1.9.20"
}

repositories { mavenCentral() }

group = "br.com.crazycrowd.bugs.jacksoninlineclassserialization"

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("com.fasterxml.jackson.core:jackson-core")
  implementation("com.fasterxml.jackson.core:jackson-databind")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation(kotlin("test"))
  testImplementation("io.mockk:mockk:1.13.5")
}

java { sourceCompatibility = JavaVersion.VERSION_21 }

kotlin { jvmToolchain(21) }

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs += "-Xjsr305=strict"
    jvmTarget = "21"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()

  ignoreFailures = true
}
