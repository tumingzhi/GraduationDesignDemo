package helloworld.tumingzhi.com.graduationdesigndemo;

import android.text.TextUtils;

import helloworld.tumingzhi.com.graduationdesigndemo.bean.User;
import helloworld.tumingzhi.com.graduationdesigndemo.utils.SPUtils;

/**
 * Created by DELL on 2018/3/4.
 */

public class UserInfoHolder {
    private static UserInfoHolder mInstance=new UserInfoHolder();
    private User mUser;

    private static final String KEY_USERNAME="key_username";

    public static UserInfoHolder getmInstance(){
        return mInstance;
    }
    public void setUser(User user) throws IllegalAccessException {
        mUser=user;
        if (user!=null){
            SPUtils.getInstance().put(KEY_USERNAME,user.getUsername());
        }
    }
    public User getUser(){
        User u=mUser;
        if (u==null){
            String username= (String) SPUtils.getInstance().get(KEY_USERNAME,"");
            if (!TextUtils.isEmpty(username)){
                u=new User();
                u.setUsername(username);
            }
        }
        mUser=u;
        return u;
    }
}
