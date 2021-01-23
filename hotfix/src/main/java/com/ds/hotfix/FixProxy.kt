package com.ds.hotfix

class FixProxy: ChangeQuickRedirect {

    override fun isSupport(methodSignature: String, `object`: Any): Boolean {
        return true
    }

    override fun accessDispatch(methodSignature: String, paramArrayOfObject: Array<Any>?): Any? {
        return null
    }
}