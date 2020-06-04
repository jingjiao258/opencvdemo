1.环境：-----ndk 21.0.6113669
          -----opencv 4.3.0

项目结构：
1、其中opencv Module 中是OpenCV-android-sdk\sdk\java中的代码，同时在Module中在main的目录下创建一个路径目录aidl/org/opencv/engine，以及把java/org/opencv/engine/OpencOpenCVEngineInterface.aidl文件移动到刚创建的目录下。
2、在app主程序下的sdk 是OpenCV-android-sdk下的sdk
由于这些文件都比较大所以就没有上传服务器，自己下载OpenCV-android-sdk导入

CMakeLists.txt配置：

# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html


# Sets the minimum version of CMake required to build the native library.


cmake_minimum_required(VERSION 3.4.1)


# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
# ##################### OpenCV 环境 ############################
#设置OpenCV-android-sdk路径
set( OpenCV_DIR ../sdk/native/jni )


find_package(OpenCV REQUIRED )
if(OpenCV_FOUND)
    include_directories(${OpenCV_INCLUDE_DIRS})
    message(STATUS "OpenCV library status:")
    message(STATUS "    version: ${OpenCV_VERSION}")
    message(STATUS "    libraries: ${OpenCV_LIBS}")
    message(STATUS "    include path: ${OpenCV_INCLUDE_DIRS}")
else(OpenCV_FOUND)
    message(FATAL_ERROR "OpenCV library not found")
endif(OpenCV_FOUND)


# ###################### 项目原生模块 ###########################


add_library( # Sets the name of the library.
        native-lib


        # Sets the library as a shared library.
        SHARED


        # Provides a relative path to your source file(s).
        native-lib.cpp )


find_library( # Sets the name of the path variable.
        log-lib


        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log )


target_link_libraries( native-lib
        ${log-lib}
        ${OpenCV_LIBS}
        jnigraphics)



gradle配置

apply plugin: 'com.android.application'


android {
    compileSdkVersion 29
    buildToolsVersion "29.0.3"


    defaultConfig {
        applicationId "com.jing.opencvdemo"
        minSdkVersion 21
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"


        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        externalNativeBuild {
            cmake {
                cppFlags ""
                arguments "-DANDROID_STL=c++_shared"
            }
        }
    }


    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    externalNativeBuild {
        cmake {
            path "src/main/cpp/CMakeLists.txt"
            version "3.10.2"
        }
    }
    sourceSets{
        main{
            jniLibs.srcDirs = ["libs"];
        }
    }
}


dependencies {
    implementation fileTree(dir: "libs", include: ["*.jar"])
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation project(path: ':opencv')
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'


}

参考链接：https://www.jianshu.com/p/6e16c0429044
https://www.pianshen.com/article/3689421638/

