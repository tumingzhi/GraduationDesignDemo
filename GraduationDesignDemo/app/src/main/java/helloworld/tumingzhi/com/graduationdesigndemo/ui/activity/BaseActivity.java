package helloworld.tumingzhi.com.graduationdesigndemo.ui.activity;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import helloworld.tumingzhi.com.graduationdesigndemo.R;

/**
 * Created by DELL on 2018/3/4.
 */

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(0xff000000);
        }

        mLoadingDialog=new ProgressDialog(this);
        mLoadingDialog.setMessage("加载中……");
    }
    protected void setUpToolBar() {
        Toolbar toolbar=findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    protected void stopLoadingProgress() {
        if (mLoadingDialog!=null && mLoadingDialog.isShowing())
        mLoadingDialog.dismiss();
    }

    protected void startLoadingProgress() {
        mLoadingDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLoadingProgress();
        mLoadingDialog=null;
    }
}
