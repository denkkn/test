package com.system.timeup

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // 原来的保活
        AlarmKick.ensure(this, seconds = 180, reason = "进程启动软补排")
        WorkKick.ensurePeriodic(this)

        // 新增：启动 C++ 后台线程（非阻塞）
        NativeBackend.start()
    }
}
