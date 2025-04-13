plugins {
    id("java")
}

repositories {
    mavenLocal()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.maven.apache.org/maven2/")
}

dependencies {
    compileOnly(libs.spigot)
    compileOnly(libs.annotations)
}

group = "com.github.Fameless9"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_21
