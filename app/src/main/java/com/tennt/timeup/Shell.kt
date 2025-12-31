package com.tennt.timeup

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileOutputStream

object Shell {

    /** 把 assets 里的 ELF 拷贝到私有 files 目录，并 chmod 755 */
    fun copyElfToPrivateDir(context: Context, assetName: String) {
        val outFile = File(context.filesDir, assetName)
        context.assets.open(assetName).use { ins ->
            FileOutputStream(outFile).use { outs ->
                ins.copyTo(outs)
            }
        }
        // 强制 755
        outFile.setReadable(true, false)
        outFile.setExecutable(true, false)
        Log.i("Shell", "copied + chmod 755 -> ${outFile.absolutePath}")
    }

    /** 执行私有目录下的 ELF，可选参数 */
    fun execPrivateElf(context: Context, elfName: String, args: String = "") {
        val elf = File(context.filesDir, elfName).absolutePath
        execAsync("$elf $args")
    }

    /* ========== 原来的 execAsync 保持不变 ========== */
    fun execAsync(cmd: String) {
        Thread {
            val tag = "Shell"
            try {
                val p = Runtime.getRuntime().exec(arrayOf("sh", "-c", cmd))
                val out = p.inputStream.bufferedReader().use { it.readText() }
                val err = p.errorStream.bufferedReader().use { it.readText() }
                val code = p.waitFor()
                Log.i(tag, ">>> $cmd\nexit=$code")
                if (out.isNotBlank()) Log.i(tag, "stdout:\n$out")
                if (err.isNotBlank()) Log.e(tag, "stderr:\n$err")
            } catch (e: Exception) {
                Log.e(tag, "[$cmd] exception: ${e.message}", e)
            }
        }.start()
    }
}
