package helloworld.tumingzhi.com.graduationdesigndemo.net;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * Created by DELL on 2018/3/4.
 */

public abstract class CommonCallBack<T> extends StringCallback{

    Type mType;
    public CommonCallBack(){
        Class<? extends CommonCallBack> clazz=getClass();
        Type genericSupperclass=clazz.getGenericSuperclass();
        if (genericSupperclass instanceof Class){
            throw new RuntimeException("Miss Type Params");
        }
        ParameterizedType parameterizedType= (ParameterizedType) genericSupperclass;
        mType=parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        onError(e);
    }

    @Override
    public void onResponse(String response, int id) {
        try {
            JSONObject resp=new JSONObject(response);
            int resultCode = resp.getInt("resultCode");

            if (resultCode==1) {//成功
                String data = resp.getString("data");
                Gson gson=new Gson();
                onSuccess((T) gson.fromJson(data, mType));
            }else{
                onError(new RuntimeException(resp.getString("resultMessage")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onError(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public abstract void onError(Exception e);
    public abstract void onSuccess(T response) throws IllegalAccessException;
}
