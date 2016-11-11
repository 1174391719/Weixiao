package com.maxi.weixiao;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maxi_160514.network.HttpProvider;

/**
 * Created by mingzhi.yuan on 10/25/16.
 */

public class NovelHelper {
    static String TAG = "NovelHelper";
    static NovelHelper mNovelHelper = null;

    private HttpProvider mHttpProvider = new HttpProvider();

    private NovelHelper() {
        mHttpProvider = new HttpProvider();
    }

    public static NovelHelper getInstance() {
        return mNovelHelper != null ? mNovelHelper : (mNovelHelper = new NovelHelper());
    }


    public void getAllNovels(final GetAllNovels callBack) {
        final Map map = new HashMap();
        map.put("key", "2");
        new Thread() {
            public void run() {
                List<Novel> novelList = null;
                JSONObject js = mHttpProvider.httpPost(Constants.webURL, map);
                if (js != null) {
                    novelList = getNovelsResultParser(js);
                    if (novelList != null) {
                        callBack.getAllNovels(novelList);
                    }
                }
            }
        }.start();
    }

    public List getNovelsResultParser(JSONObject js) {
        List<Novel> novels = null;
        if (js != null) {
            try {
                String jo = js.getString("result");
                JSONArray jsonArray = new JSONArray(jo);
                if (jsonArray != null) {
                    novels = new ArrayList<>();
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
                        novels.add(novel);
                    }
                }
                return novels;
            } catch (Exception e) {
                Log.d("kk", "e:" + e.toString());
            }
        }
        return null;
    }

    public void getParts(final GetParts callBack) {

        final Map map = new HashMap();
        map.put("key", "3");
        map.put("novelId", 3);

        new Thread() {
            public void run() {

                JSONObject js = mHttpProvider.httpPost(Constants.webURL, map);
                if (js != null) {
                    Log.d("kk", "getParts-----------:" + js.toString());
                    try {
                        String jo = js.getString("result");


                        JSONArray jsonArray = new JSONArray(jo);
                        if (jsonArray != null) {
                            Log.d("kk", "getParts----22-------jsonArray:" + jsonArray);

                            List<Part> parts = new ArrayList<>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jb = jsonArray.getJSONObject(i);

                                if (jb != null) {
                                    Part part = new Part();
                                    String id = jb.getString("novel_id");
                                    part.setNovelId(Integer.parseInt(jb.getString("novel_id")));
                                    part.setTitle(jb.getString("title"));
                                    part.setPartPosition(Integer.parseInt(jb.getString("part_posation")));
                                    parts.add(part);
                                    Log.d("kk" + TAG, "getParts----------getNovelId:" + part.getNovelId() +
                                            " getPartPosition:" + part.getPartPosition());
                                }
                            }
                            callBack.getParts(parts);
                        }


                    } catch (Exception e) {
                        Log.d("kk" + TAG, "Exception in getParts .  e:" + e.toString());
                    }
                }
            }
        }.start();
    }

    public void getSections(final GetSections callBack, int novelId, int partPosition) {
        final Map map = new HashMap();
        map.put("key", "4");
        map.put("novelId", novelId);
        map.put("partPosition", partPosition);

        new Thread() {
            public void run() {

                JSONObject js = mHttpProvider.httpPost(Constants.webURL, map);
                if (js != null) {
                    Log.d("kk", "getSections-----------:" + js.toString());
                    try {
                        String jo = js.getString("result");
                        JSONArray jsonArray = new JSONArray(jo);
                        if (jsonArray != null) {
                            Log.d("kk", "getSections----22-------jsonArray:" + jsonArray);

                            List<Section> sections = new ArrayList<>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jb = jsonArray.getJSONObject(i);

                                if (jb != null) {
                                    Section section = new Section();
                                    section.setNovelId(Integer.parseInt(jb.getString("novel_id")));
                                    section.setPosition(Integer.parseInt(jb.getString("part_posation")));
                                    section.setContent(jb.getString("content"));
                                    sections.add(section);
                                }
                            }
                            callBack.getSections(sections);
                        }
                    } catch (Exception e) {
                        Log.d("kk" + TAG, "Exception in getParts .  e:" + e.toString());
                    }
                }
            }
        }.start();
    }

    public void getNovels(final GetNovels callBack, int count) {
        final Map map = new HashMap();
        map.put("key", "5");
        map.put("count", count);
        new Thread() {
            public void run() {
                JSONObject js = mHttpProvider.httpPost(Constants.webURL, map);
                if (js != null) {
                    List<Novel> novels = getNovelsResultParser(js);
                    if (novels != null) {
                        callBack.equals(novels);
                    }
                }
            }
        }.start();
    }


    public interface GetAllNovels {
        void getAllNovels(List<Novel> novels);
    }

    public interface GetParts {
        void getParts(List<Part> parts);
    }

    public interface GetSections {
        void getSections(List<Section> sections);
    }

    public interface GetNovels {
        void getNovels(List<Novel> novels);
    }

}
