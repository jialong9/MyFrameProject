package com.vcyber.myframe.ui.activity;

import android.util.Log;

import com.google.gson.Gson;

/**
 * description ：
 * author : zjl
 * date : 9/22/21
 */
public class TestJava {
    public static void main(String[] args) {
        int out = test(549);
        System.out.println("zjl===" + out);
    }

    private static int test(int times) {
        String json = "{\"first\":5,\"rate\":20,\"ncfg\":[{\"times\":[-1,5],\"rate\":20},{\"times\":[6,10],\"rate\":30},{\"times\":[11,-1],\"rate\":50}]}";
        TestJavaBean bean = new Gson().fromJson(json, TestJavaBean.class);
        int allAnswer = 0;
        for (int i = 0; i < bean.ncfg.size(); i++) {
            int time = bean.ncfg.get(i).times[1];
            int rate = bean.ncfg.get(i).rate;
            if (time == -1) {
                return ((times - allAnswer) / rate + 1) * rate + allAnswer;
            } else {
                allAnswer += time * rate;
            }
            if (times < allAnswer) {
                int interval = times - (allAnswer - time * rate);
                return (interval / rate + 1) * rate + (allAnswer - time * rate);
            }
        }
        return Integer.MAX_VALUE;
    }
} 