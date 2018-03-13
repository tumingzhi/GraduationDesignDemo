package helloworld.tumingzhi.com.graduationdesigndemo.utils;

import com.google.gson.Gson;

/**
 * Created by DELL on 2018/3/4.
 */

public class GsonUtil {
    public static Gson mGson=new Gson();

    public static Gson getGson() {
        return mGson;
    }
}
