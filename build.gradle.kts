plugins {
    id("java")
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.eclipse.jetty:jetty-server:11.0.7")
    implementation("org.eclipse.jetty:jetty-servlet:11.0.7")
    implementation("jakarta.servlet:jakarta.servlet-api:5.0.0")
    implementation("org.eclipse.jetty:jetty-servlets:11.0.7")
    implementation("com.google.code.gson:gson:2.8.9")

    implementation("org.slf4j:slf4j-simple:2.0.0-alpha5")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass.set("server.JettyServer")
}

tasks.test {
    useJUnitPlatform()
}