package com.system.timeup

import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader

object Shell {

    /** 非阻塞地执行一条 shell 命令（当前 UID） */
    fun execAsync(cmd: String) {
        Thread {
            var reader: BufferedReader? = null
            try {
                val p = Runtime.getRuntime().exec(cmd)
                reader = BufferedReader(InputStreamReader(p.inputStream))
                val err = BufferedReader(InputStreamReader(p.errorStream))

                val sb = StringBuilder()
                reader.lineSequence().forEach { sb.appendLine(it) }
                err.lineSequence().forEach { sb.appendLine(it) }

                val code = p.waitFor()
                Log.i("Shell", "[$cmd] exit=$code\n$sb")
            } catch (e: Exception) {
                Log.e("Shell", "[$cmd] error", e)
            } finally {
                reader?.close()
            }
        }.start()
    }
}
