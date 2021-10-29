import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.31"
	kotlin("plugin.spring") version "1.5.31"
	kotlin("plugin.jpa") version "1.5.31" // @Entity, @Embeddedable, @MappedSuperClass 에 No-args 를 자동으로 생성
	id("org.jetbrains.kotlin.plugin.allopen") version "1.3.61" // Hibernate에선 final 을 사용하면 안되기에 open 상태로 만듬
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.5.5")
	implementation("org.springframework.boot:spring-boot-starter-mustache:2.5.5")
	implementation("org.springframework.boot:spring-boot-starter-security:2.5.5")
	implementation("org.springframework.boot:spring-boot-starter-web:2.5.5")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	compileOnly("org.projectlombok:lombok:1.18.22")
	developmentOnly("org.springframework.boot:spring-boot-devtools:2.5.5")
	runtimeOnly("org.postgresql:postgresql:42.2.24.jre7")
	annotationProcessor("org.projectlombok:lombok:1.18.22")
	testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.5")
	testImplementation("org.springframework.security:spring-security-test:5.5.1")
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
