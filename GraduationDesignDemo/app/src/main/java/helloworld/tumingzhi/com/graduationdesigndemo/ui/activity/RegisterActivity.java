package helloworld.tumingzhi.com.graduationdesigndemo.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import helloworld.tumingzhi.com.graduationdesigndemo.R;
import helloworld.tumingzhi.com.graduationdesigndemo.bean.User;
import helloworld.tumingzhi.com.graduationdesigndemo.biz.UserBiz;
import helloworld.tumingzhi.com.graduationdesigndemo.net.CommonCallBack;
import helloworld.tumingzhi.com.graduationdesigndemo.utils.T;

public class RegisterActivity extends BaseActivity {

    private EditText edt_username,edt_password,edt_repassword;
    private Button mBtnRegister;
    private UserBiz mUserBiz=new UserBiz();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setUpToolBar();
        initView();
        initEvent();
        setTitle("注册");
    }


    private void initEvent() {
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        String username=edt_username.getText().toString();
                        String password=edt_password.getText().toString();
                        String repassword=edt_repassword.getText().toString();

                        if (TextUtils.isEmpty(username)||TextUtils.isEmpty(password)||TextUtils.isEmpty(repassword)){
                            Toast.makeText(RegisterActivity.this,"帐号或密码不能为空",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!password.equals(repassword)){
                            Toast.makeText(RegisterActivity.this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        startLoadingProgress();
                        mUserBiz.register(username, password, new CommonCallBack<User>() {
                            @Override
                            public void onError(Exception e) {
                                stopLoadingProgress();
                                T.showToast(e.getMessage());
                            }

                            @Override
                            public void onSuccess(User response) throws IllegalAccessException {
                                stopLoadingProgress();
                                T.showToast("注册成功，用户名为:"+response.getUsername());
                                LoginActivity.launch(RegisterActivity.this,response.getUsername(),response.getPassword());
                                finish();
                            }
                        });
            }
        });
    }

    private void initView() {
        edt_username=findViewById(R.id.edt_username);
        edt_password=findViewById(R.id.edt_password);
        edt_repassword=findViewById(R.id.edt_repassword);
        mBtnRegister=findViewById(R.id.btn_register);
    }
}
