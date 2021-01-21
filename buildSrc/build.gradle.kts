plugins {
    `kotlin-dsl`
}

//定义资源目录
val javaSourcePath = "src/main/java"
sourceSets.main.get().java.srcDir(javaSourcePath)

repositories {
    //gradle tools
    google()
    mavenCentral()
    //kotlin-dsl
    jcenter()

}

dependencies {
    api(gradleApi())
    api(localGroovy())
    //gradle工具
    implementation("com.android.tools.build:gradle:4.1.1")

    //ASM依赖
    implementation("org.ow2.asm:asm:8.0")
    implementation("org.ow2.asm:asm-util:8.0")
    implementation ("org.ow2.asm:asm-commons:8.0")

    //常用io操作
    implementation("commons-io:commons-io:2.6")

    implementation("com.google.code.gson:gson:2.8.1")
}