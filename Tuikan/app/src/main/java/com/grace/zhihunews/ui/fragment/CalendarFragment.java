package com.grace.zhihunews.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.grace.zhihunews.R;
import com.grace.zhihunews.network.entity.SignCalendarReq;
import com.grace.zhihunews.ui.base.BaseActivity;
import com.grace.zhihunews.ui.view.SignCalendar;
import com.grace.zhihunews.util.CalendarDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarFragment extends BaseActivity {

    private View.OnClickListener listener;
    private static boolean isShown;

    private SignCalendar calendar;
    private String date;
    //private TextView btn_sign;
    private TextView tv_sign_year_month;
    private SignCalendarReq signCalendarReq;
    private SignCalendarReq.DataBean dataBean;
    List<String> list = new ArrayList<>();//list中存储的格式为2019-06-02
    private int month;
    private int year;
    private RelativeLayout rlGetGiftData;
    private TextView tvGetSunValue;
    private ImageView ivSun;
    private ImageView ivSunBg;
    private RelativeLayout rlQuedingBtn;
    //private RelativeLayout rlBtnSign;
    private ImageView signBack;
    private boolean isSign;
    private TextView btn_sign;
    private RelativeLayout rlBtnSign;

    // public static CalendarFragment newInstance(){
    //  Bundle arguments = new Bundle();
    //  CalendarFragment tabContentFragment = new CalendarFragment();
    //  tabContentFragment.setArguments(arguments);
    //
    //  isShown = false;
    //  System.out.println("调用一次:Calendar fragment newInstance()方法");
    //
    //  return tabContentFragment;
    // }
    // private void initData() {
    //
    // }


    private String getSignDaysAndCheckToday(String year, String month, Context context, String date_today) {
        String signDays = "";

        CalendarDao calendarDao = new CalendarDao();
        String dates = calendarDao.listDay(year, month, context);
        //System.out.println("dates: " + dates);
        String datesArr[] = dates.split(",");
        for (int i = 0; i < datesArr.length; i++) {
            if (date_today.equals(datesArr[i])) {
                isSign = true;
            }
            if (i == 0) {
                signDays = datesArr[i].substring(8);
            } else {
                signDays = signDays + "," + datesArr[i].substring(8);  //yyyy-MM-dd，取"dd"
            }
        }
        System.out.println("signDays: " + signDays);

        return signDays;
    }


    private void signCalendarData() {
        //模拟请求后台数据签到已成功


        rlGetGiftData.setVisibility(View.VISIBLE);
        //  rlBtnSign.setBackgroundResource(R.drawable.btn_sign_calendar_no);


        btn_sign.setText("已签到");
        isSign = true;//模拟当天已签到*/

        String text = "暂无今日数据";
        if (isSign) {
            text = "恭喜获得两个地瓜干";
        } else {
            text = "完成任务再来打卡吧";
        }

        //  String text = "恭喜你已完成今日计划";

        ivSun.setImageResource(R.drawable.i8live_sun);
        tvGetSunValue.setText(text);

        Animation operatingAnim = AnimationUtils.loadAnimation(getApplication().getApplicationContext(), R.anim.rotate_anim_online_gift);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        ivSunBg.startAnimation(operatingAnim);

        //list.add("2017-11-18");
        list.add(date);
        calendar.addMarks(list, 0);
        isSign = true;

    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sign_calendar);
        //System.out.println("调用initData()方法");
        initData();

        //获取当前的月份
        month = Calendar.getInstance().get(Calendar.MONTH);
        //获取当前的年份
        year = Calendar.getInstance().get(Calendar.YEAR);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当前时间
        Date curDate = new Date(System.currentTimeMillis());
        date = formatter.format(curDate);

        calendar = (SignCalendar) findViewById(R.id.sc_main);
        btn_sign = (TextView) findViewById(R.id.btn_sign);
        tv_sign_year_month = (TextView) findViewById(R.id.tv_sign_year_month);
        rlGetGiftData = (RelativeLayout) findViewById(R.id.rl_get_gift_view);  //弹出签到提示
        tvGetSunValue = (TextView) findViewById(R.id.tv_text_one);
        ivSun = (ImageView) findViewById(R.id.iv_sun);
        ivSunBg = (ImageView) findViewById(R.id.iv_sun_bg);
        signBack = (ImageView) findViewById(R.id.i8show_attention_back);
        rlQuedingBtn = (RelativeLayout) findViewById(R.id.rl_queding_btn);
        rlBtnSign = (RelativeLayout) findViewById(R.id.rl_btn_sign);


        //设置当前日期
        tv_sign_year_month.setText(year + "年" + (month + 1) + "月");

        if (signCalendarReq != null) {
            if (signCalendarReq.getState().getCode() == 1) {//1成功，0失败
                dataBean = signCalendarReq.getData();
                //获取当月已签到的日期
                String signDay = dataBean.getSignDay();
                String[] splitDay = signDay.split(",");

                System.out.println("已签到日期: " + signDay);

                //list中存储的格式为2019-06-02
                for (int i = 0; i < splitDay.length; i++) {
                    if (Integer.parseInt(splitDay[i]) < 10) {
                        if (month < 10) {
                            list.add(year + "-0" + (month + 1) + "-0" + splitDay[i]);
                        } else {
                            list.add(year + "-" + (month + 1) + "-0" + splitDay[i]);
                        }

                    } else {
                        if (month < 10) {
                            list.add(year + "-0" + (month + 1) + "-" + splitDay[i]);
                        } else {
                            list.add(year + "-" + (month + 1) + "-" + splitDay[i]);
                        }
                    }
                }


                //给当月已签到日期加上标记
                calendar.addMarks(list, 0);
            }


            btn_sign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isSign) {
                        signCalendarData();
                        btn_sign.setText("已签到");
                    }
                }
            });
            rlQuedingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rlGetGiftData.setVisibility(View.GONE);
                }
            });

            listener = new View.OnClickListener() {
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.i8show_attention_back:
                            finish();
                            break;
                    }
                }
            };
            signBack.setOnClickListener(listener);
        }

    }

    private void initData() {
        isSign = false;

        signCalendarReq = new SignCalendarReq();

        SignCalendarReq.StateBean state = new SignCalendarReq.StateBean();
        state.setCode(1);
        state.setMsg("成功");
        signCalendarReq.setState(state);

        SignCalendarReq.DataBean data = new SignCalendarReq.DataBean();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());
        String curDateStr = formatter.format(curDate);
        data.setConSign(1);
        String signDays = getSignDaysAndCheckToday(curDateStr.substring(0, 4), curDateStr.substring(5, 7), getApplication(), curDateStr);
        data.setSignDay(signDays);
        data.setIsSign(0);
        if(isSign){
            data.setIsSign(1);
        }
        data.setUid("3347922");
        signCalendarReq.setData(data);

        System.out.println("init_seq: " + signCalendarReq.getData().getUid() + ", " + signCalendarReq.getData().getSignDay() + "," + signCalendarReq.getData().getIsSign());

    }


    @Override
    protected void loadData() {

    }
}