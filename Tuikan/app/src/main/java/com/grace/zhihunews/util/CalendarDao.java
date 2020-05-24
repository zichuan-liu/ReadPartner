package com.grace.zhihunews.util;

import android.annotation.SuppressLint;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarDao {
    private final static String CALENDAR_NAME = "calendar.txt";



    public String listDay(String year, String month, Context context) {
        SDUtil sdUtil = new SDUtil(context);
        StringBuilder res = new StringBuilder();

        try {
            String dayListStr = sdUtil.readFromSD(CALENDAR_NAME);

            String[] dayArr = dayListStr.split(",");
            if ( dayArr.length == 0 ) {
                return "";
            }
            for (int i = 0; i < dayArr.length - 1; i++) {
                String dayStr = dayArr[i];
                String[] speDayArr = dayStr.split("-");
                if (speDayArr[0].equals(year) && speDayArr[1].equals(month)) {
                    res.append(dayStr).append(",");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res.substring(0, res.length());
    }


    public void punch(Context context) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        WRUtil wrUtil = new WRUtil();

        String currentDate = dateFormat.format(new Date());
        System.out.println(currentDate);

        try {
            wrUtil.writeFile(context, currentDate, RecordType.CALENDAR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}