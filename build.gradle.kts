import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.serialization") version "1.4.10"
    id("com.github.johnrengelman.shadow") version "4.0.4"
}

val mainClass = "net.mt32.mtstats.MainKt"
group = "net.mt32"
version = "1.0"

repositories {
    jcenter()
}

dependencies {
    testImplementation(kotlin("test-junit"))
    implementation(platform("org.http4k:http4k-bom:3.285.1"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-netty")
    implementation("org.http4k:http4k-client-apache")
    implementation(group = "org.http4k", name = "http4k-format-kotlinx-serialization", version = "3.285.1")
    implementation(group = "org.http4k", name = "http4k-graphql", version = "3.285.1")
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveBaseName.set("shadow")
    mergeServiceFiles()
    manifest {
        attributes(mapOf("Main-Class" to mainClass))
    }
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}