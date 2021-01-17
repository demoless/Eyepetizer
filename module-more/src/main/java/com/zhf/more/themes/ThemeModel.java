package com.zhf.more.themes;

import com.zhf.base.model.BaseModel;
import com.zhf.base.utils.GsonUtils;
import com.zhf.more.themes.bean.TabInfo;
import com.zhf.http.EasyHttp;
import com.zhf.http.callback.SimpleCallBack;
import com.zhf.http.exception.ApiException;

import io.reactivex.disposables.Disposable;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 */
public class ThemeModel<T> extends BaseModel<T> {

    private Disposable disposable;

    @Override
    protected void load() {
        disposable = EasyHttp.get("/api/v7/tag/tabList")
                  .cacheKey(getClass().getSimpleName())
                  .execute(new SimpleCallBack<String>() {
                      @Override
                      public void onError(ApiException e) {
                          loadFail(e.getMessage());
                      }

                      @Override
                      public void onSuccess(String s) {
                         parseData(s);
                      }
                  });
    }

    @Override
    public void cancel() {
        super.cancel();
        EasyHttp.cancelSubscription(disposable);
    }

    private void parseData(String s) {
        TabInfo tabInfo = GsonUtils.fromLocalJson(s, TabInfo.class);
        if (tabInfo != null){
            loadSuccess((T) tabInfo.getTabInfo().getTabList());
        }
    }
}
