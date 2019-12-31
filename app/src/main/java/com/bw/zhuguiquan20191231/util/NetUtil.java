package com.bw.zhuguiquan20191231.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.zhuguiquan20191231.R;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * DateTime:2019/12/31 0031
 * author:朱贵全(Administrator)
 * function:
 */
public class NetUtil {
    private static NetUtil netUtil;
    private final Handler handler;
    private final OkHttpClient okHttpClient;

    private NetUtil() {
        handler = new Handler();
        //设置拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                //设置时间
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }
    //单例模式
    public static NetUtil getInstance() {
        if(netUtil==null){
            synchronized (NetUtil.class){
                if(netUtil==null){
                    netUtil=new NetUtil();
                }
            }
        }
        return netUtil;
    }
    //获取json的get方法
    public void getJsonGet(String http,MyCallBack myCallBack){
        Request request = new Request.Builder()
                .get()
                .url(http)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallBack.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.ongetJson(string);
                        }
                    });
                }else{
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.onError(new Throwable("获取失败"));
                        }
                    });
                }
            }
        });
    }
    //获取json的post方法
    public void getJsonPost(String http, Map<String,String> map,MyCallBack myCallBack){
        FormBody.Builder builder = new FormBody.Builder();
        for(String key:map.keySet()){
            builder.add(key,map.get(key));
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(http)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallBack.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.ongetJson(string);
                        }
                    });
                }else{
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.onError(new Throwable("获取失败"));
                        }
                    });
                }
            }
        });
    }
    //获取图片
    public void getPhoto(String str, ImageView imageView){
        Glide.with(imageView)
                .load(str)
                //错误图
                .error(R.mipmap.ic_launcher)
                //展位图
                .placeholder(R.mipmap.ic_launcher_round)
                //圆角
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(80)))
                .into(imageView);
    }
    //胖多是否有网的方法
    public boolean hasNet(Context context){
        ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
        if (activeNetworkInfo != null&&activeNetworkInfo.isAvailable()) {
            return true;
        }else{
            return false;
        }
    }
    public interface MyCallBack{
        void ongetJson(String json);
        void onError(Throwable throwable);
    }
}
