package com.grace.zhihunews.network.entity;

import java.io.Serializable;

public class SignCalendarReq implements Serializable {

    /**
     * data : {"conSign":2,"isSign":1,"signDay":"20,21","uid":"3347922"}
     * state : {"code":1,"msg":"成功"}
     */

    private DataBean data;
    private StateBean state;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public static class DataBean implements Serializable {
        /**
         * conSign : 2
         * isSign : 1
         * signDay : 20,21
         * uid : 3347922
         */

        private int conSign;
        private int isSign;
        private String signDay;
        private String uid;

        public int getConSign() {
            return conSign;
        }

        public void setConSign(int conSign) {
            this.conSign = conSign;
        }

        public int getIsSign() {
            return isSign;
        }

        public void setIsSign(int isSign) {
            this.isSign = isSign;
        }

        public String getSignDay() {
            return signDay;
        }

        public void setSignDay(String signDay) {
            this.signDay = signDay;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }

    public static class StateBean implements Serializable {
        /**
         * code : 1
         * msg : 成功
         */

        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}