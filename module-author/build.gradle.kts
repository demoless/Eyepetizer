import com.four.buildsrc.*

apply(from= "../module.build.gradle")

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    kotlin("kapt")
}

android {
    defaultConfig {
        if (com.four.buildsrc.util.PropertiesUtil.getBooleanProperty("isBuildModule", false, project)){
            applicationId("com.zhf.author")
        }
    }
    //统一资源前缀，规范资源引用
    resourcePrefix("author_")

    sourceSets.getAt("main").java.srcDir("src/main/java")

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

    kapt {
        arguments {
            // 路由框架译配置
            // module名称
            arg("AROUTER_MODULE_NAME", project.name)
        }
    }
}

dependencies {
    implRepo(Dep.kotlinStdlib)
    implRepo(Dep.coreKTX)
    implRepo(Dep.appcompat)
    implRepo(Dep.googleMaterial)
    implRepo(Dep.constraintLayout)
    kapt(Dep.arouter_compiler)
    androidTestImpl(Dep.junitExt)
    androidTestImpl(Dep.espressoCore)
    //组件依赖基础库
    apiProject(":library-common")
}
