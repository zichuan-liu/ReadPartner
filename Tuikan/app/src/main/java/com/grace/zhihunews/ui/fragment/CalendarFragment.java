package com.grace.zhihunews.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.grace.zhihunews.R;
import com.grace.zhihunews.network.entity.SignCalendarReq;
import com.grace.zhihunews.ui.activity.MainActivity;
import com.grace.zhihunews.ui.view.SignCalendar;
import com.grace.zhihunews.util.CalendarDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarFragment extends Fragment {

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

 public static CalendarFragment newInstance(){
  Bundle arguments = new Bundle();
  CalendarFragment tabContentFragment = new CalendarFragment();
  tabContentFragment.setArguments(arguments);

  isShown = false;
  System.out.println("调用一次:Calendar fragment newInstance()方法");

  return tabContentFragment;
 }


 @RequiresApi(api = Build.VERSION_CODES.M)
 @Nullable
 @Override
 public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        /*if(dataBean !=null){
            System.out.println("dataBean 0/1: " + dataBean.getIsSign());
        }
        else{
            System.out.println("dataBean: null");
        }*/

  View contentView = inflater.inflate(R.layout.activity_sign_calendar, null);
        /*ImageView i8show_attention_back = (ImageView) contentView.findViewById(R.id.i8show_attention_back);
        TextView i8show_attention_tittle = (TextView) contentView.findViewById(R.id.i8show_attention_tittle);
        TextView tv_sign_year_month = (TextView) contentView.findViewById(R.id.tv_sign_year_month);
        TextView btn_sign = (TextView) contentView.findViewById(R.id.btn_sign);
        ImageView iv_huode = (ImageView) contentView.findViewById(R.id.iv_huode);
        ImageView iv_sun_bg = (ImageView) contentView.findViewById(R.id.iv_sun_bg);
        ImageView iv_sun = (ImageView) contentView.findViewById(R.id.iv_sun);
        TextView tv_text_one = (TextView) contentView.findViewById(R.id.tv_text_one);*/

  initData();

  //savedInstanceState.putSerializable("userInfos", (Serializable) signCalendarReq);
  //模拟传值

  //接收传递过来的数据
  //final SignCalendarReq signCalendarReq = (SignCalendarReq) getActivity().getIntent().getSerializableExtra("userInfos");
  //System.out.println("init_seq: " + signCalendarReq.getData().getUid() + ", " + signCalendarReq.getData().getSignDay() + "," + signCalendarReq.getData().getIsSign());

  //获取当前的月份
  month = Calendar.getInstance().get(Calendar.MONTH);
  //获取当前的年份
  year = Calendar.getInstance().get(Calendar.YEAR);

  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  // 获取当前时间
  Date curDate = new Date(System.currentTimeMillis());
  date = formatter.format(curDate);

  calendar = (SignCalendar) contentView.findViewById(R.id.sc_main);
  //btn_sign = (TextView) contentView.findViewById(R.id.btn_sign);
  tv_sign_year_month = (TextView) contentView.findViewById(R.id.tv_sign_year_month);
  rlGetGiftData = (RelativeLayout) contentView.findViewById(R.id.rl_get_gift_view);  //弹出签到提示
  tvGetSunValue = (TextView) contentView.findViewById(R.id.tv_text_one);
  ivSun = (ImageView) contentView.findViewById(R.id.iv_sun);
  ivSunBg = (ImageView) contentView.findViewById(R.id.iv_sun_bg);
  signBack = (ImageView) contentView.findViewById(R.id.i8show_attention_back);
  rlQuedingBtn = (RelativeLayout) contentView.findViewById(R.id.rl_queding_btn);
  //rlBtnSign = (RelativeLayout) contentView.findViewById(R.id.rl_btn_sign);

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

    //若检测到今天已打卡，则“可”弹出提示框（一次？）
                /*if(isShown == false && isSign == true){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            signCalendarData();
                            isShown = true;
                        }
                    }, 400);
                    //signCalendarData();
                }
                else if(isShown == true && isSign == true){
                    System.out.println("已显示过完成任务提示");
                }
                else if(isShown == false && isSign == false){
                    System.out.println("未完成任务，未显示过提示");
                }
                else if(isShown == true && isSign == false){
                    System.out.println("可能发生了错误的提示显示");
                }*/


                /*if (dataBean.getIsSign() == 1) {//1是已签到，0是未签到
                    rlBtnSign.setBackgroundResource(R.drawable.btn_sign_calendar_no);
                    btn_sign.setText("已签到");
                    rlBtnSign.setClickable(false);
                } else {
                    rlBtnSign.setBackgroundResource(R.drawable.btn_sign_calendar);
                    btn_sign.setText("签 到");
                }*/
   }
  }

        /*btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSign) {
                    signCalendarData();
                }
            }
        });
        rlQuedingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlGetGiftData.setVisibility(View.GONE);
            }
        });*/

        /*signBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
            }
        });*/

  listener = new View.OnClickListener() {
   public void onClick(View v) {
    Intent intent = new Intent();
    switch (v.getId()) {
     case R.id.i8show_attention_back:
      //finish();
      intent.setClass(getActivity(), MainActivity.class);
      break;
    }
    startActivity(intent);
   }
  };
  signBack.setOnClickListener(listener);
  return contentView;
 }

 @RequiresApi(api = Build.VERSION_CODES.M)
 private void initData() {
  //System.out.println("调用initData()方法");
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
  String signDays = getSignDaysAndCheckToday(curDateStr.substring(0, 4), curDateStr.substring(5, 7), getContext(), curDateStr);
  data.setSignDay(signDays);
  data.setIsSign(0);
  if(isSign){
   data.setIsSign(1);
  }
  data.setUid("3347922");
  signCalendarReq.setData(data);

  System.out.println("init_seq: " + signCalendarReq.getData().getUid() + ", " + signCalendarReq.getData().getSignDay() + "," + signCalendarReq.getData().getIsSign());

        /*
        //模拟请求后台返回初始化数据
        //SignCalendarReq local_signCalendarReq = new SignCalendarReq();
        signCalendarReq = new SignCalendarReq();
        SignCalendarReq.StateBean state = new SignCalendarReq.StateBean();
        state.setCode(1);
        state.setMsg("成功");
        signCalendarReq.setState(state);
        SignCalendarReq.DataBean data = new SignCalendarReq.DataBean();
        data.setConSign(1);
        data.setIsSign(0);
        data.setSignDay("1,2");
        data.setUid("3347922");
        signCalendarReq.setData(data);
        //System.out.println("init_seq: " + signCalendarReq.getData().getUid() + ", " + signCalendarReq.getData().getSignDay() + "," + signCalendarReq.getData().getIsSign());
        */

 }


 private String getSignDaysAndCheckToday(String year, String month, Context context, String date_today){
  String signDays = "";

  CalendarDao calendarDao = new CalendarDao();
  String dates = calendarDao.listDay(year, month, context);
  //System.out.println("dates: " + dates);
  String datesArr[] = dates.split(",");
  for(int i = 0;i < datesArr.length;i++){
   if(date_today.equals(datesArr[i])){
    isSign = true;
   }
   if(i == 0){
    signDays = datesArr[i].substring(8);
   }
   else{
    signDays = signDays + "," + datesArr[i].substring(8);  //yyyy-MM-dd，取"dd"
   }
  }
  System.out.println("signDays: " + signDays);

  return signDays;
 }


 private void signCalendarData() {
  //模拟请求后台数据签到已成功


  rlGetGiftData.setVisibility(View.VISIBLE);
  //rlBtnSign.setBackgroundResource(R.drawable.btn_sign_calendar_no);


        /*btn_sign.setText("已签到");
        isSign = true;//模拟当天已签到*/

        /*String text = "暂无今日数据";
        if(isSign){
            text = "恭喜获得10个阳光值";
        }
        else{
            text = "完成任务再来打卡吧";
        }*/

  String text = "恭喜你已完成今日计划";

  ivSun.setImageResource(R.drawable.i8live_sun);
  tvGetSunValue.setText(text);

  Animation operatingAnim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_anim_online_gift);
  LinearInterpolator lin = new LinearInterpolator();
  operatingAnim.setInterpolator(lin);
  ivSunBg.startAnimation(operatingAnim);

  //list.add("2017-11-18");
  list.add(date);
  calendar.addMarks(list, 0);

 }

}