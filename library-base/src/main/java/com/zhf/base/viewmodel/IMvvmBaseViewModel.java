package com.zhf.base.viewmodel;

/**
 * 应用模块: viewModel
 * <p>
 * 类描述: 定义viewModel与 V 的关联
 * <p>
 *
 */
public interface IMvvmBaseViewModel<V> {
    /**
     * 关联View
     * */
    void attachUi(V view);

    /**
     * 获取View
     * */
    V getPageView();

    /**
     * 是否已经关联View
     * */
    boolean isUiAttach();

    /**
     * 解除关联
     * */
    void detachUi();
}
