package com.grace.zhihunews.ui.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.grace.zhihunews.R;

/**
 * author : 刘子川
 * e-mail : 775269512@qq.com
 * date   : 2020/5/2315:54
 * version: 1.0
 */
public class ExchangeActivity extends Activity {
    private Button exchange_bt, tv_back, btn_all_ex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }


    //获取界面控件
    private void init() {
        exchange_bt= (Button) findViewById(R.id.exchange_bt);
        tv_back= (Button) findViewById(R.id.tv_back);
        btn_all_ex=  (Button) findViewById(R.id.btn_all_ex);

        exchange_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exchange_bt.setText("兑换成功");
                exchange_bt.setBackgroundColor(Color.LTGRAY);
            }
        });

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //登录界面销毁
                ExchangeActivity.this.finish();
            }
        });
        btn_all_ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_all_ex.setText("全部兑换");
                btn_all_ex.setBackgroundColor(Color.LTGRAY);
                exchange_bt.setText("已兑换");
                exchange_bt.setBackgroundColor(Color.LTGRAY);
            }
        });
    }
}
