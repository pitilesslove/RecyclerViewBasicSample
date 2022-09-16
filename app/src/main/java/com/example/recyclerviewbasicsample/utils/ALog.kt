package com.example.recyclerviewbasicsample.utils

import android.util.Log
import com.example.recyclerviewbasicsample.BuildConfig

/**
 * Logging Class
 * */
object ALog {
    private val currentLineInfo: String
        get() {
            val trace = Thread.currentThread().stackTrace[4]
            val strFileName = trace.fileName
            val strMethodName = trace.methodName
            val nLineNumber = trace.lineNumber

            return "[F:$strFileName][T:${getThreadName()}][M:$strMethodName][L:$nLineNumber] : "
        }

    private fun getThreadName(): String {
        var threadName = Thread.currentThread().name
        if (threadName.length > 30) {
            threadName = threadName.substring(0, 30) + "..."
        }
        return threadName
    }

    fun v(tag:String, parMessage: String) {
        Log.v(tag, currentLineInfo + parMessage)
    }
    fun v(parMessage: String) {
        Log.v(TAG, currentLineInfo + parMessage)
    }

    fun d(tag:String,parMessage: String) {
        if(BuildConfig.DEBUG)
            Log.d(tag, currentLineInfo + parMessage)
    }
    fun d(parMessage: String) {
        if(BuildConfig.DEBUG)
            Log.d(TAG, currentLineInfo + parMessage)
    }

    fun i(tag:String,parMessage: String) {
        Log.i(tag, currentLineInfo + parMessage)
    }
    fun i(parMessage: String) {
        Log.i(TAG, currentLineInfo + parMessage)
    }

    fun w(tag:String, parMessage: String) {
        Log.w(tag, currentLineInfo + parMessage)
    }
    fun w(parMessage: String) {
        Log.w(TAG, currentLineInfo + parMessage)
    }

    fun e(tag:String, parMessage: String) {
        Log.e(tag, currentLineInfo + parMessage)
    }
    fun e(parMessage: String) {
        Log.e(TAG, currentLineInfo + parMessage)
    }
    fun e(tag:String, parMessage:String, e:Throwable){
        Log.e(tag, currentLineInfo +parMessage, e)
    }
}