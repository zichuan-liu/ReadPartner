package com.grace.zhihunews.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SDUtil {
    private Context context;

    public SDUtil() {
    }

    public SDUtil(Context context) {
        super();
        this.context = context;
    }

    public void initFile() {
        try {
            WRUtil wrUtil = new WRUtil();
            String calendar = readFromSD(RecordType.CALENDAR.getPath());
            if (calendar == null || calendar.equals("")) {
                wrUtil.writeFile(context, "2020-05-19", RecordType.CALENDAR);
                wrUtil.writeFile(context, "2020-05-20", RecordType.CALENDAR);
                wrUtil.writeFile(context, "2020-05-21", RecordType.CALENDAR);
                wrUtil.writeFile(context, "2020-05-22", RecordType.CALENDAR);
            }
            String today = readFromSD(RecordType.TODAY.getPath());
            if ( today == null || today.equals("") ) {
                wrUtil.writeFile(context, "15", RecordType.TODAY);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //往SD卡写入文件的方法
    public void saveFileToSD(String filename, String content) throws Exception {
        //如果手机已插入sd卡,且app具有读写sd卡的权限
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + filename;
            //这里就不要用openFileOutput了,那个是往手机内存中写数据的
            File file = new File(filename);
            String dirPath = Environment.getExternalStorageDirectory().getCanonicalPath() + "/";
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!file.exists()) {
                boolean newFile = file.createNewFile();
            }
            FileOutputStream output = new FileOutputStream(filename);
            output.write(content.getBytes());
            //将String字符串以字节流的形式写入到输出流中
            output.close();
            //关闭输出流
        } else Toast.makeText(context, "SD卡不存在或者不可读写", Toast.LENGTH_SHORT).show();
    }

    public void appendFileToSD(String filename, String content) throws Exception {
        //如果手机已插入sd卡,且app具有读写sd卡的权限
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + filename;
            //这里就不要用openFileOutput了,那个是往手机内存中写数据的
            File file = new File(filename);
            String dirPath = Environment.getExternalStorageDirectory().getCanonicalPath() + "/";
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!file.exists()) {
                boolean newFile = file.createNewFile();
                FileOutputStream output = new FileOutputStream(filename, true);
                String preData = "2019-06-19,2019-06-20,2019-06-21,2019-06-22";
                output.write(preData.getBytes());
                output.close();

            }
            FileOutputStream output = new FileOutputStream(filename, true);
            content += ",";
            output.write(content.getBytes());
            //将String字符串以字节流的形式写入到输出流中
            output.close();
            //关闭输出流
        } else Toast.makeText(context, "SD卡不存在或者不可读写", Toast.LENGTH_SHORT).show();
    }


    //读取SD卡中文件的方法
    //定义读取文件的方法:
    public String readFromSD(String filename) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + filename;
            //打开文件输入流
            FileInputStream input = new FileInputStream(filename);
            byte[] temp = new byte[1024];

            int len = 0;
            //读取文件内容:
            while ((len = input.read(temp)) > 0) {
                sb.append(new String(temp, 0, len));
            }
            //关闭输入流
            input.close();
        }
        return sb.toString();
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};


    public void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}