package com.bw.zhuguiquan20191231.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.zhuguiquan20191231.R;
import com.bw.zhuguiquan20191231.base.BaseActivity;
import com.bw.zhuguiquan20191231.contract.IHomeContract;
import com.bw.zhuguiquan20191231.model.bean.Bean;
import com.bw.zhuguiquan20191231.presenter.HomePresenter;
import com.bw.zhuguiquan20191231.util.NetUtil;
import com.bw.zhuguiquan20191231.view.adapter.MyAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<HomePresenter> implements IHomeContract.IView {


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void initData() {
        mpresenter.getHomeData();

    }

    @Override
    protected void initView() {

    }

    @Override
    protected HomePresenter providePresenter() {
        return new HomePresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onHomeSuccess(Bean bean) {
        //判断是否有网
        if(NetUtil.getInstance().hasNet(this)){
            //获取数据
            List<Bean.RankingBean> list = bean.getRanking();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rv.setLayoutManager(linearLayoutManager);
            //创建适配器
            MyAdapter myAdapter = new MyAdapter(list);
            //条目点击事件
            myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(MainActivity.this, ""+list.get(position).getName(), Toast.LENGTH_SHORT).show();
                }
            });
            //设置适配器
            rv.setAdapter(myAdapter);
        }else{
            Toast.makeText(this, "没有网络", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onHomeFailure(Throwable throwable) {

    }
    @OnClick(R.id.tv)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this,SecondActivity.class));
    }
}
