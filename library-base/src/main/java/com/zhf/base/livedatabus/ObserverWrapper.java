package com.zhf.base.livedatabus;

import androidx.lifecycle.Observer;

/**
 * 应用模块: liveData
 * <p>
 * 类描述: Observer 包装类
 * <p>
 *
 */
public class ObserverWrapper<T> implements Observer<T> {

    private final Observer<T> observer;

    public ObserverWrapper(Observer<T> observer) {
        this.observer = observer;
    }

    @Override
    public void onChanged(T t) {
       if (observer != null){
           if (isCallOnObserve()){
               return;
           }
           observer.onChanged(t);
       }
    }

    private boolean isCallOnObserve() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length > 0) {
            for (StackTraceElement element : stackTrace) {
                if ("android.arch.lifecycle.LiveData".equals(element.getClassName()) &&
                        "observeForever".equals(element.getMethodName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
