package com.system.timeup

object NativeBackend {
    init {
        System.loadLibrary("timeup_backend")   // 对应 CMake 里的模块名
    }

    @JvmStatic
    external fun start()   // 对应 C++ 里的 Java_com_system_timeup_NativeBackend_start
}
