package com.maxi.weixiao;

import android.util.Log;

import org.json.JSONObject;

import java.util.Map;

import maxi_160514.network.HttpProvider;

/**
 * Created by mingzhi.yuan on 3/17/16.
 */
public class HttpHelp {

    public void register(final NetInterface.register register, final Map map) {
        new Thread() {
            public void run() {

                HttpProvider hs = new HttpProvider();
                JSONObject js = hs.httpPost(Constants.webURL, map);
                Log.d("kk", "register js:" + js);
                if (js != null) {
                    try {
                        String str = js.getString("key");
                        Log.d("kk", "register str:" + str);
                        if (!str.equals("0")) {
                            register.registerCallBack(true);
                        } else {
                            register.registerCallBack(false);
                        }
                    } catch (Exception e) {

                    }
                }
            }
        }.start();
    }

    public void login(final NetInterface.Login login, final Map map) {
        new Thread() {
            public void run() {

                HttpProvider hs = new HttpProvider();

                JSONObject js = hs.httpPost(Constants.webURL, map);
                //       hs.doPost(Constants.webURL);
                if (js != null) {
                    try {
                        String str = js.getString("key");
                        Log.d("kk", "register str:" + str);
                        if (!str.equals("0")) {
                            login.loginCallBack(true);
                        } else {
                            login.loginCallBack(false);
                        }
                    } catch (Exception e) {

                    }
                }
            }
        }.start();
    }

}
