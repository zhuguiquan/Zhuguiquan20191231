package com.bw.zhuguiquan20191231.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.zhuguiquan20191231.R;
import com.bw.zhuguiquan20191231.base.BaseActivity;
import com.bw.zhuguiquan20191231.base.BasePresenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends BaseActivity {

    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.bt1)
    Button bt1;
    @BindView(R.id.bt2)
    Button bt2;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //生成二维码
        Bitmap s1 = CodeUtils.createImage("朱贵全", 400, 400, null);
        imageview.setImageBitmap(s1);
        //长按点击事件
        imageview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CodeUtils.analyzeByImageView(imageview, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(SecondActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(SecondActivity.this, "失败", Toast.LENGTH_SHORT).show();

                    }
                });
                return true;
            }
        });


    }

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_second;
    }

    @OnClick({R.id.bt1, R.id.bt2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                //点击吐司微信
                EventBus.getDefault().post("微信");
                break;
            case R.id.bt2:
                //点击吐司QQ
                EventBus.getDefault().post("QQ");
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getWei(String str){
        Toast.makeText(this, ""+str, Toast.LENGTH_SHORT).show();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getQQ(String sss){
        Toast.makeText(this, ""+sss, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //绑定
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        EventBus.getDefault().unregister(this);
    }
}
