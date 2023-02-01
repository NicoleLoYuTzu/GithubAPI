package com.example.dcardhomework.util

import android.util.Log
import com.example.dcardhomework.BuildConfig


//和业务逻辑没有关系的数据处理
object Logger {

    private const val TAG = "Nicole-DcardHomework"

    fun i(content: String) { if (BuildConfig.LOGGER_VISIABLE) Log.i(TAG, content) }
    fun w(content: String) { if (BuildConfig.LOGGER_VISIABLE) Log.w(TAG, content) }
}
