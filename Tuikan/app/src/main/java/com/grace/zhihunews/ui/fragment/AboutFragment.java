package com.grace.zhihunews.ui.fragment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.grace.zhihunews.R;
import com.grace.zhihunews.ui.activity.LoginActivity;

/**
 * Created by Administrator on 2016/9/3.
 */
public class AboutFragment extends AppCompatActivity {
    private Button btn_quit, tv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_about);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    //获取界面控件
    private void init() {
        btn_quit= (Button) findViewById(R.id.btn_quit);
        tv_back= (Button) findViewById(R.id.tv_back);


        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutFragment.this.finish();
                startActivity(new Intent(AboutFragment.this, LoginActivity.class));
//                Intent intent = new Intent(getApplication(), LoginActivity.class);
//                getApplication().startActivity(intent);
            }
        });
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //登录界面销毁
                AboutFragment.this.finish();
            }
        });
    }

}
