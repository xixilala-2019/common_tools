# 集成步骤

1.使项目支持C++

```groovy
android {
    compileSdkVersion 30
    buildToolsVersion "30.0.3"

    defaultConfig {
       ***

        externalNativeBuild {
            cmake {
                cppFlags ""
                cFlags "-DSTDC_HEADERS"  // 必须，给编译器加上STDC头文件的宏定义
//                abiFilters "armeabi-v7a" // 可选，指定生成so的兼容的架构
            }
        }
    }
```



