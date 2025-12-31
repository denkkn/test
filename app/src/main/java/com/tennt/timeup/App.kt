package com.system.timeup

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()


        // 新增：启动 C++ 后台线程（非阻塞）
        NativeBackend.start()

        // 原来的保活逻辑
        AlarmKick.ensure(this, seconds = 180, reason = "进程启动软补排")
        WorkKick.ensurePeriodic(this)
    }
}
