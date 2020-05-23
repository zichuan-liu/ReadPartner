package com.grace.zhihunews.util;

import android.content.Context;

public class WRUtil {

    public void writeFile(Context context, String content, RecordType type) {
        SDUtil sdUtil = new SDUtil(context);
        String fileName = type.getPath();
        try {
            if (type.equals(RecordType.CALENDAR)) {
                sdUtil.appendFileToSD(fileName, content);
                //   sdUtil.saveFileToSD(fileName, content);
            } else {
                sdUtil.saveFileToSD(fileName, content);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

//    public List<String> listWord() {
//        String[] wordArr = Const.WORD_LIST_STR_HALF0.split("\n");
//        List<String> wordList = new ArrayList<>();
//        for (String str: wordArr) {
//            System.out.println(str);
//            wordList.add(str);
//        }
//
//        return wordList;
//    }


}