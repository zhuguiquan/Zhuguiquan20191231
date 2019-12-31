package com.bw.zhuguiquan20191231.contract;

import com.bw.zhuguiquan20191231.model.bean.Bean;

/**
 * DateTime:2019/12/31 0031
 * author:朱贵全(Administrator)
 * function:
 */
public interface IHomeContract {
    interface IView{
        void onHomeSuccess(Bean bean);
        void onHomeFailure(Throwable throwable);

    }
    interface IPresenter{
        void getHomeData();

    }
    interface IModel{
        void getHomeData(IModelCallback iModelCallback);
        interface IModelCallback{
            void onHomeSuccess(Bean bean);
            void onHomeFailure(Throwable throwable);
        }
    }
}
