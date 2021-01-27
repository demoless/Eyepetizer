package com.zhf.main.utils

import android.content.Context
import androidx.core.content.ContextCompat

/**
 * created by demoless on 2021/1/27
 * description:
 */
object ColorUtil {
    @JvmStatic
    fun getColor(context: Context?, colorId: Int): Int {
        return ContextCompat.getColor(context!!, colorId)
    }
}