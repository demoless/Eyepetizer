package com.ds.hotfix;

import android.util.Log;


@HotFix
public class Fix {
    public static ChangeQuickRedirect changeQuickRedirect;
    @FixModifier
    public void test() {
        changeQuickRedirect = new FixProxy();
        if (changeQuickRedirect != null) {
            if (changeQuickRedirect.isSupport("test",this)) {
                changeQuickRedirect.accessDispatch("test",null);
                Log.e("as","fix invoke!");
            }
        }
    }

    void te() {
        System.out.println("hello world");
        Log.d("log","hello world!");
    }
}
