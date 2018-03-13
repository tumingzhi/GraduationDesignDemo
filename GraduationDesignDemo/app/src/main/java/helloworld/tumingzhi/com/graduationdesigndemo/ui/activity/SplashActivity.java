package helloworld.tumingzhi.com.graduationdesigndemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import helloworld.tumingzhi.com.graduationdesigndemo.R;

public class SplashActivity extends AppCompatActivity {

    private Button btn_skip;
    private Handler mHandler=new Handler();
    private Runnable mRunableToLogin=new Runnable() {
        @Override
        public void run() {
            ToLoginActivity();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initEvent();
        mHandler.postDelayed(mRunableToLogin,3000);
    }

    private void initEvent() {
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandler.removeCallbacks(mRunableToLogin);
                ToLoginActivity();
            }
        });
    }

    private void initView() {
        btn_skip = findViewById(R.id.btn_id_skip);
    }

    private void ToLoginActivity(){
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunableToLogin);
    }
}
