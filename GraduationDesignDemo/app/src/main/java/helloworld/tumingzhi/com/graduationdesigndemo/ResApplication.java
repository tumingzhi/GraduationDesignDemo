package helloworld.tumingzhi.com.graduationdesigndemo;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;

import java.util.concurrent.TimeUnit;

import helloworld.tumingzhi.com.graduationdesigndemo.utils.SPUtils;
import helloworld.tumingzhi.com.graduationdesigndemo.utils.T;
import okhttp3.OkHttpClient;

/**
 * Created by DELL on 2018/3/4.
 */

public class ResApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        T.init(this);
        SPUtils.init(this,"sp_user.pref");

        CookieJarImpl cookieJar=new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));

        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
