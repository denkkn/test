android {
    compileSdk = 35

    defaultConfig {
        applicationId = "com.system.timeup"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        externalNativeBuild {
            ndkBuild {
                // 如有需要可传参：
                // arguments += "APP_PLATFORM=android-21"
            }
        }
    }

    // 关键：指定 Android.mk 路径
    externalNativeBuild {
        ndkBuild {
            path("src/main/cpp/Android.mk")
        }
    }
}
