import com.four.buildsrc.*

apply(Pair("from", file("../module.build.gradle")))

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    defaultConfig {
        if (com.four.buildsrc.util.PropertiesUtil.getBooleanProperty("isBuildModule", false, project)){
            applicationId("com.zhf.home")
        }
    }
    //统一资源前缀，规范资源引用
    resourcePrefix("home_")

    sourceSets.getAt("main").java.srcDir("src/main/java")
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions{
        sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implRepo(Dep.kotlinStdlib)
    implRepo(Dep.coreKTX)
    implRepo(Dep.appcompat)
    implRepo(Dep.googleMaterial)
    implRepo(Dep.constraintLayout)
    apiRepo(Dep.arouter_api)
    kapt(Dep.arouter_compiler)
    androidTestImpl(Dep.junitExt)
    androidTestImpl(Dep.espressoCore)
    //组件依赖基础库
   apiProject(":library-common")
}
