package com.system.timeup

import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader

object Shell {

    fun execAsync(cmd: String) {
        Thread {
            val tag = "Shell"
            try {
                val p = Runtime.getRuntime().exec(arrayOf("sh", "-c", cmd))   // 显式 sh -c
                val out = p.inputStream.bufferedReader().use { it.readText() }
                val err = p.errorStream.bufferedReader().use { it.readText() }
                val code = p.waitFor()

                val log = buildString {
                    appendLine(">>> $cmd")
                    appendLine("exit=$code")
                    if (out.isNotBlank()) appendLine("stdout:\n$out")
                    if (err.isNotBlank()) appendLine("stderr:\n$err")
                }

                Log.i(tag, log)          // 1. 打印到 logcat
                FileLog.i(tag, log)      // 2. 同时写到你已有的文件日志
            } catch (e: Exception) {
                val msg = "[$cmd] exception: ${e.message}"
                Log.e(tag, msg, e)
                FileLog.e(tag, msg)
            }
        }.apply { name = "shell"; start() }
    }
}
