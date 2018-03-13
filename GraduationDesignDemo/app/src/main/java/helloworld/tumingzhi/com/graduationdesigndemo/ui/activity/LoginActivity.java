package helloworld.tumingzhi.com.graduationdesigndemo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;

import helloworld.tumingzhi.com.graduationdesigndemo.R;
import helloworld.tumingzhi.com.graduationdesigndemo.UserInfoHolder;
import helloworld.tumingzhi.com.graduationdesigndemo.bean.User;
import helloworld.tumingzhi.com.graduationdesigndemo.biz.UserBiz;
import helloworld.tumingzhi.com.graduationdesigndemo.net.CommonCallBack;
import helloworld.tumingzhi.com.graduationdesigndemo.utils.T;

/**
 * Created by DELL on 2018/3/3.
 */

public class LoginActivity extends BaseActivity {

    private UserBiz mUserBiz=new UserBiz();
    private EditText mEdtUsername,mEdtPassword;
    private Button mBtnLogin,mBtnResgister;

    public static final String KEY_USERNAME="key_username";
    public static final String KEY_PASSWORD="key_password";

    @Override
    protected void onResume() {
        super.onResume();
        CookieJarImpl cookieJar = (CookieJarImpl) OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
        cookieJar.getCookieStore().removeAll();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
        initIntent(getIntent());
    }

    private void initEvent() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=mEdtUsername.getText().toString();
                String password=mEdtPassword.getText().toString();

                if (TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this,"请正确输入帐号密码",Toast.LENGTH_SHORT).show();
                    return;
                }

                startLoadingProgress();
                mUserBiz.login(username, password, new CommonCallBack<User>() {
                    @Override
                    public void onError(Exception e) {
                        stopLoadingProgress();
                        T.showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(User response) throws IllegalAccessException {
                        stopLoadingProgress();
                        T.showToast("登录成功");
                        //保存用户信息

                        UserInfoHolder.getmInstance().setUser(response);
                        ToOrderActivity();
                    }
                });
            }
        });
        mBtnResgister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToRegisterActivity();
            }
        });
    }

    private void ToRegisterActivity() {
        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    private void ToOrderActivity() {
        Intent intent=new Intent(this,OrderActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initIntent(intent);
    }

    private void initIntent(Intent intent) {
        if (intent==null) {
            return;
        }
        String username = intent.getStringExtra(KEY_USERNAME);
        String password = intent.getStringExtra(KEY_PASSWORD);

        if (TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
            return;
        }

        mEdtUsername.setText(username);
        mEdtPassword.setText(password);
    }

    private void initView() {
        mEdtUsername=findViewById(R.id.edt_username);
        mEdtPassword=findViewById(R.id.edt_password);
        mBtnLogin=findViewById(R.id.btn_login);
        mBtnResgister=findViewById(R.id.btn_register);
    }

    public static void launch(Context context, String username, String password) {
        Intent intent=new Intent(context,LoginActivity.class);
        //FLAG_ACTIVITY_CLEAR_TOP:跳转到的activity若已在栈中存在，则将其上的activity都销掉
        //FLAG_ACTIVITY_SINGLE_TOP:目标activity已在栈顶则跳转过去，不在栈顶则在栈顶新建activity
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(KEY_USERNAME,username);
        intent.putExtra(KEY_PASSWORD,password);
        context.startActivity(intent);
    }
}
