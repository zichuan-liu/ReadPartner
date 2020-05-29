package com.grace.zhihunews.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.grace.zhihunews.R;
import com.grace.zhihunews.ui.fragment.OnClickAddListener;

/**
 * author : 刘子川
 * e-mail : 775269512@qq.com
 * date   : 2020/5/2416:16
 * version: 1.0
 */
public class AddDialog extends Dialog {
    /**
     * 显示的图片
     */
    private ImageView imageIv ;

    /**
     * 显示的标题
     */
    private TextView titleTv ;

    /**
     * 显示的消息
     */
    private TextView messageTv ;

    /**
     * 确认和取消按钮
     */
    private Button negtiveBn ,positiveBn, bt_get_path;

    /**
     * 按钮之间的分割线
     */
    private View columnLineView ;
    public AddDialog(Context context) {
        super(context, R.style.CustomDialog);
    }

    /**
     * 都是内容数据
     */
    private String message;
    private String title;
    private String positive,negtive ;
//    private int imageResId = -1 ;

    /**
     * 底部是否只有一个按钮
     */
    private boolean isSingle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_dialog_layout);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        initView();
        //初始化界面数据
        refreshView();
        //初始化界面控件的事件
        initEvent();
    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        positiveBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( onClickAddListener!= null) {
                    onClickAddListener.onPositiveClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        negtiveBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( onClickAddListener!= null) {
                    onClickAddListener.onNegtiveClick();
                }
            }
        });
        imageIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( onClickAddListener!= null) {
                    onClickAddListener.onImgClick();
                }
            }
        });
        bt_get_path.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( onClickAddListener!= null) {
                    onClickAddListener.onPathClick();
                }
            }
        });
        titleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( onClickAddListener!= null) {
                    onClickAddListener.onTitleClick();
                }
            }
        });
        messageTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( onClickAddListener!= null) {
                    onClickAddListener.onWriteClick();
                }
            }
        });
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void refreshView() {
        if (!TextUtils.isEmpty(message)) {
            messageTv.setText(message);
        }
        //如果设置按钮的文字
        if (!TextUtils.isEmpty(positive)) {
            positiveBn.setText(positive);
        }else {
            positiveBn.setText("确定");
        }
        if (!TextUtils.isEmpty(negtive)) {
            negtiveBn.setText(negtive);
        }else {
            negtiveBn.setText("取消");
        }

        /**
         * 只显示一个按钮的时候隐藏取消按钮，回掉只执行确定的事件
         */
        if (isSingle){
            columnLineView.setVisibility(View.GONE);
            negtiveBn.setVisibility(View.GONE);
        }else {
            negtiveBn.setVisibility(View.VISIBLE);
            columnLineView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void show() {
        super.show();
        refreshView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        negtiveBn = (Button) findViewById(R.id.negtive);
        positiveBn = (Button) findViewById(R.id.positive);
        bt_get_path = (Button) findViewById(R.id.bt_get_path);
        titleTv = (TextView) findViewById(R.id.title);
        messageTv = (TextView) findViewById(R.id.message);
        imageIv = (ImageView) findViewById(R.id.image);
        columnLineView = findViewById(R.id.column_line);
    }

    /**
     * 设置确定取消按钮的回调
     */
    public OnClickAddListener onClickAddListener;
    public AddDialog setOnClickAddListener(OnClickAddListener onClickAddListener) {
        this.onClickAddListener = onClickAddListener;
        return this;
    }


    public String getMessage() {
        return message;
    }

    public AddDialog setMessage(String message) {
        this.message = message;
        return this ;
    }

    public String getTitle() {
        return title;
    }

    public AddDialog setTitle(String title) {
        this.title = title;
        return this ;
    }

    public String getPositive() {
        return positive;
    }

    public AddDialog setPositive(String positive) {
        this.positive = positive;
        return this ;
    }

    public void setBt_get_path(String bt_get_path) {
        this.bt_get_path.setText(bt_get_path);
    }

    public String getNegtive() {
        return negtive;
    }

    public AddDialog setNegtive(String negtive) {
        this.negtive = negtive;
        return this ;
    }

//    public int getImageResId() {
//        return imageResId;
//    }

    public boolean isSingle() {
        return isSingle;
    }

    public AddDialog setSingle(boolean single) {
        isSingle = single;
        return this ;
    }

}
