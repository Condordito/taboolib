plugins {
    java
    kotlin("jvm") version "1.5.10"
}

repositories {
    maven {
        isAllowInsecureProtocol = true
        url = uri("http://repo.ptms.ink/repository/maven-releases/")
    }
    maven { url = uri("https://repo.codemc.io/repository/nms/") }
    mavenCentral()
}

dependencies {
    compileOnly("ink.ptms.core:v11605:11605")
//    compileOnly("org.spigotmc:spigot:1.17-R0.1-20210612.142052-2")
    compileOnly(project(":common"))
    compileOnly(project(":common-5"))
    compileOnly(project(":plugin"))
    compileOnly(kotlin("stdlib"))
}