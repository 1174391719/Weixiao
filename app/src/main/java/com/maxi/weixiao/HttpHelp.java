package com.maxi.weixiao;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maxi_160514.network.HttpServer;

/**
 * Created by mingzhi.yuan on 3/17/16.
 */
public class HttpHelp {

    public void register(final NetInterface.register register, final Map map) {
        new Thread() {
            public void run() {

                HttpServer hs = new HttpServer();
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

                HttpServer hs = new HttpServer();

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

  //  public void getBookShelf(final NetInterface.Bookshelf book) {
    /*    new Thread() {
            public void run() {

                Constants.json=1;

                HttpServer hs = new HttpServer();
                Map<String, String> pra = new HashMap();

                List<Novel> novelList=new ArrayList<>();



                pra.put("account", "123");
                pra.put("password", "123");
           //     JSONObject js = hs.httpPost(url, pra);

                Log.d("kk", "getBookShelf:" +js.toString());

                if (js != null) {
                    try {
                        String str=js.getString("key");
                        Log.d("kk", "getBookShelf:1" );
                     String jo=js.getString("result");

                        Log.d("kk", "getBookShelf:2" );


                        JSONArray jsonArray=new JSONArray(jo);
                        Log.d("kk", "getBookShelf:3" );
                        if(jsonArray!=null){
                            for(int i=jsonArray.length()-1;i>=0;i--){
                                Log.d("kk", "getBookShelf:4" );
                                JSONObject jb=jsonArray.getJSONObject(i);
                                Novel novel=new Novel();
                                novel.setName(jb.getString("name"));
                                Log.d("kk", "name:"+jb.getString("name"));
                                novelList.add(novel);
                            }
                        }


                        book.callBack(novelList);
                    } catch (Exception e) {
                        Log.d("kk", "e:"+e.toString());
                    }
                }
            }
        }.start();*/
  //  }

    public void getLibrary(final NetInterface.InterfaceGetLibrary callBack, final Map map) {
        new Thread() {
            public void run() {

                HttpServer hs = new HttpServer();

                List<Novel> novelList = new ArrayList<>();

                JSONObject js = hs.httpPost(Constants.webURL, map);

                Log.d("kk", "getLibrary:" + js.toString());

                if (js != null) {
                    try {
                        String str = js.getString("key");
                        Log.d("kk", "getLibrary:1");
                        String jo = js.getString("result");

                        Log.d("kk", "getLibrary:2");


                        JSONArray jsonArray = new JSONArray(jo);
                        Log.d("kk", "getLibrary:3");
                        if (jsonArray != null) {
                            for (int i = jsonArray.length() - 1; i >= 0; i--) {
                                Log.d("kk", "getLibrary:4");
                                JSONObject jb = jsonArray.getJSONObject(i);
                                Novel novel = new Novel();
                                novel.setId(jb.getString("id"));
                                novel.setTitle(jb.getString("title"));
                                novel.setAccount(jb.getString("account"));
                                novel.setAuthor(jb.getString("author"));
                                novel.setBrief(jb.getString("brief"));
                                novel.setPicName(jb.getString("pic_name"));
                                novel.setEditTime(jb.getString("edit_time"));
                                novelList.add(novel);
                            }
                        }


                        callBack.getLibraly(novelList);
                    } catch (Exception e) {
                        Log.d("kk", "e:" + e.toString());
                    }
                }
            }
        }.start();
    }

}
