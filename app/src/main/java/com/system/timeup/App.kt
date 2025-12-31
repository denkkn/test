package com.system.timeup

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        Shell.execAsync("echo '123' | nc 127.0.0.1 8870")

        // 你原来的保活逻辑
        FileLog.i(this, "应用进程启动：执行软补排（闹钟3分钟 + 周期保险）")
        AlarmKick.ensure(this, seconds = 180, reason = "进程启动软补排")
        WorkKick.ensurePeriodic(this)
    }
}
