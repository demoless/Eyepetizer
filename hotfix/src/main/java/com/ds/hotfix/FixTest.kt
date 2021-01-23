package com.ds.hotfix

import android.content.Context
import android.util.Log
@HotFix
class FixTest {

    @FixModifier
    fun test() {
        Log.d("tag","test invoke")
    }

    fun launchFixTest(context: Context) {
        test()
    }
}