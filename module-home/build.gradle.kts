import com.four.buildsrc.*

apply(Pair("from", file("../module.build.gradle")))

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    defaultConfig {
        if (com.four.buildsrc.util.PropertiesUtil.getBooleanProperty("isBuildModule", false, project)){
            applicationId("com.drz.home")
        }
    }
    //统一资源前缀，规范资源引用
    resourcePrefix("home_")

    sourceSets.getAt("main").java.srcDir("src/main/java")
}

dependencies {
    implRepo(Dep.kotlinStdlib)
    implRepo(Dep.coreKTX)
    implRepo(Dep.appcompat)
    implRepo(Dep.googleMaterial)
    implRepo(Dep.constraintLayout)
    androidTestImpl(Dep.junitExt)
    androidTestImpl(Dep.espressoCore)
    //组件依赖基础库
   apiProject(":library-common")
}
