package com.bw.zhuguiquan20191231.presenter;

import com.bw.zhuguiquan20191231.base.BasePresenter;
import com.bw.zhuguiquan20191231.contract.IHomeContract;
import com.bw.zhuguiquan20191231.model.HomeModel;
import com.bw.zhuguiquan20191231.model.bean.Bean;

/**
 * DateTime:2019/12/31 0031
 * author:朱贵全(Administrator)
 * function:
 */
public class HomePresenter extends BasePresenter<IHomeContract.IView>implements IHomeContract.IPresenter {

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        //实例化homemodel
        homeModel = new HomeModel();
    }

    @Override
    public void getHomeData() {
        homeModel.getHomeData(new IHomeContract.IModel.IModelCallback() {
            @Override
            public void onHomeSuccess(Bean bean) {
                view.onHomeSuccess(bean);
            }

            @Override
            public void onHomeFailure(Throwable throwable) {
                view.onHomeFailure(throwable);

            }
        });

    }
}
