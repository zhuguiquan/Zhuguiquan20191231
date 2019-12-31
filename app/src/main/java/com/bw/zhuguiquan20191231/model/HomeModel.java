package com.bw.zhuguiquan20191231.model;

import android.util.Log;

import com.bw.zhuguiquan20191231.contract.IHomeContract;
import com.bw.zhuguiquan20191231.model.bean.Bean;
import com.bw.zhuguiquan20191231.util.NetUtil;
import com.google.gson.Gson;

/**
 * DateTime:2019/12/31 0031
 * author:朱贵全(Administrator)
 * function:
 */
public class HomeModel implements IHomeContract.IModel {

    @Override
    public void getHomeData(IModelCallback iModelCallback) {
        //调用方法
        NetUtil.getInstance().getJsonGet("http://blog.zhaoliang5156.cn/api/news/ranking.json", new NetUtil.MyCallBack() {
            @Override
            public void ongetJson(String json) {
                Log.i("Xxx",json);
                //解析gson
                Bean bean = new Gson().fromJson(json, Bean.class);
                iModelCallback.onHomeSuccess(bean);

            }

            @Override
            public void onError(Throwable throwable) {

                iModelCallback.onHomeFailure(throwable);

            }
        });
    }
}
