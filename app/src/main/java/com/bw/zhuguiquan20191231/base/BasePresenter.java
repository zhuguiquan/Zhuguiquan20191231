package com.bw.zhuguiquan20191231.base;

/**
 * DateTime:2019/12/31 0031
 * author:朱贵全(Administrator)
 * function:
 */
public abstract class BasePresenter<V> {
    protected V view;

    public BasePresenter() {
      initModel();
    }

    protected abstract void initModel();

    public void attach(V view) {
        this.view = view;
    }
    public void detach(){
        view=null;
    }
}
