package helloworld.tumingzhi.com.graduationdesigndemo.biz;

import com.zhy.http.okhttp.OkHttpUtils;

import helloworld.tumingzhi.com.graduationdesigndemo.bean.User;
import helloworld.tumingzhi.com.graduationdesigndemo.config.Config;
import helloworld.tumingzhi.com.graduationdesigndemo.net.CommonCallBack;

/**
 * Created by DELL on 2018/3/4.
 */

public class UserBiz {
    public void login(String username, String password, CommonCallBack<User>commonCallBack){
        OkHttpUtils
                .post().url(Config.baseUrl+"user_login")
        .tag(this)
        .addParams("username",username)
        .addParams("password",password)
        .build()
        .execute(commonCallBack);
    }
    public void register(String username, String password, CommonCallBack<User>commonCallBack){
        OkHttpUtils
                .post().url(Config.baseUrl+"user_register")
                .tag(this)
                .addParams("username",username)
                .addParams("password",password)
                .build()
                .execute(commonCallBack);
    }
}
