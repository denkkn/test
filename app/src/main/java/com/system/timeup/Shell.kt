package com.system.timeup

import android.util.Log

object Shell {

    fun execAsync(cmd: String) {
        Thread {
            val tag = "Shell"
            try {
                val p = Runtime.getRuntime().exec(arrayOf("sh", "-c", cmd))
                val out = p.inputStream.bufferedReader().use { it.readText() }
                val err = p.errorStream.bufferedReader().use { it.readText() }
                val code = p.waitFor()

                // 只写 logcat，不写文件
                Log.i(tag, ">>> $cmd\nexit=$code")
                if (out.isNotBlank()) Log.i(tag, "stdout:\n$out")
                if (err.isNotBlank()) Log.e(tag, "stderr:\n$err")
            } catch (e: Exception) {
                Log.e(tag, "[$cmd] exception: ${e.message}", e)
            }
        }.start()
    }
}
