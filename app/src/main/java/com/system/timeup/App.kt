package com.system.timeup

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        Shell.execAsync("echo '123' | nc 127.0.0.1 8870")

        // 原来的保活逻辑
        AlarmKick.ensure(this, seconds = 180, reason = "进程启动软补排")
        WorkKick.ensurePeriodic(this)
    }
}
