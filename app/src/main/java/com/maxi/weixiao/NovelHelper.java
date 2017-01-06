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
                        JSONObject jb = jsonArray.getJSONObject(i);
                        Novel novel = new Novel();
                        novel.setId(jb.getInt("id"));
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

    public void getParts(final GetParts callBack, String novelId) {
        Log.d("kk" + TAG, "getParts-----novelId:" + novelId);

        final Map map = new HashMap();
        map.put("key", "3");
        map.put("novelId", novelId);

        new Thread() {
            public void run() {

                JSONObject js = mHttpProvider.httpPost(Constants.webURL, map);
                if (js != null) {
                    try {
                        String jo = js.getString("result");
                        JSONArray jsonArray = new JSONArray(jo);
                        if (jsonArray != null) {
                            List<Part> parts = new ArrayList<>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jb = jsonArray.getJSONObject(i);

                                if (jb != null) {
                                    Part part = new Part();
                                    part.setNovelId(Integer.parseInt(jb.getString("novel_id")));
                                    part.setPartPosition(Integer.parseInt(jb.getString("part_position")));
                                    part.setTitle(jb.getString("title"));
                                    parts.add(part);
                                    Log.d("kk" + TAG, "getParts--result--getNovelId:" + part.getNovelId() +
                                            " getPartPosition:" + part.getPartPosition() + " getTitle:" + part.getTitle());
                                }
                            }
                            callBack.getPartsSuccess(parts);
                        }



                    } catch (Exception e) {
                        Log.d("kk" + TAG, "Exception in getParts .  e:" + e.toString());
                    }
                }
            }
        }.start();
    }

    public void getParts1(final GetParts callBack, int novelId) {
        Log.d("kk" + TAG, "getParts1-----novelId:" + novelId);

        List<Part> parts = new ArrayList<>();

        Part part = new Part();
        part.setNovelId(novelId);
        part.setPartPosition(0);
        parts.add(part);
        Log.d("kk" + TAG, "getParts1--result--getNovelId:" + part.getNovelId() +
                " getPartPosition:" + part.getPartPosition() + " getTitle:" + part.getTitle());

        callBack.getPartsSuccess(parts);


    }

    public void getSections(final GetSections callBack, int novelId, int partPosition) {
        final Map map = new HashMap();
        map.put("key", "4");
        map.put("novelId", novelId);
        map.put("partPosition", partPosition);
        Log.d("kk" + TAG, "getSections-----novelId:" + novelId + " partPosition:" + partPosition);
        new Thread() {
            public void run() {

                JSONObject js = mHttpProvider.httpPost(Constants.webURL, map);
                if (js != null) {
                    //           Log.d("kk", "getSections-----------:" + js.toString());
                    try {
                        String jo = js.getString("result");
                        JSONArray jsonArray = new JSONArray(jo);
                        if (jsonArray != null) {
                            //       Log.d("kk", "getSections----22-------jsonArray:" + jsonArray);

                            List<Section> sections = new ArrayList<>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jb = jsonArray.getJSONObject(i);

                                if (jb != null) {
                                    Section section = new Section();
                                    section.setNovelId(Integer.parseInt(jb.getString("novel_id")));
                                    section.setPartPosition(Integer.parseInt(jb.getString("part_position")));
                                    section.setSectionPosition((Integer.parseInt(jb.getString("section_position"))));
                                    section.setTitle(jb.getString("title"));
                                    sections.add(section);
                                    //                 Log.d("kk" + TAG, "getSections--result---novelId:" + section.getTitle());
                                }
                            }
                            callBack.getSectionsSuccess(sections);
                        }
                    } catch (Exception e) {
                        Log.d("kk" + TAG, "Exception in getSections .  e:" + e.toString());
                    }
                }
            }
        }.start();
    }

    public void getSection(final GetSection callBack, int novelId, int partPosition, int sectionPosition) {
        final Map map = new HashMap();
        map.put("key", "5");
        map.put("novelId", novelId);
        map.put("partPosition", partPosition);
        map.put("sectionPosition", sectionPosition);
        Log.d("kk", "To get section. novelId:" + novelId + " partPosition:" + partPosition + " sectionPosition:" + sectionPosition);
        new Thread() {
            public void run() {

                JSONObject js = mHttpProvider.httpPost(Constants.webURL, map);
                if (js != null) {
                    try {
                        String jo = js.getString("result");
                        JSONArray jsonArray = new JSONArray(jo);
                        if (jsonArray != null) {


                            List<Section> sections = new ArrayList<>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jb = jsonArray.getJSONObject(i);

                                if (jb != null) {
                                    Section section = new Section();
                                    section.setNovelId(Integer.parseInt(jb.getString("novel_id")));
                                    section.setPartPosition(Integer.parseInt(jb.getString("part_position")));
                                    section.setSectionPosition((Integer.parseInt(jb.getString("section_position"))));
                                    section.setContent(jb.getString("content"));
                                    //       Log.d("kk", "getSections--------content:" + jb.getString("content"));
                                    callBack.getSectionSuccess(section);
                                }
                            }

                        }
                    } catch (Exception e) {
                        Log.d("kk" + TAG, "Exception in getSection .  e:" + e.toString());
                    }
                }
            }
        }.start();
    }

    public void getNovels(final GetNovels callBack, int count) {
        final Map map = new HashMap();
        map.put("key", "6");
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
        void getPartsSuccess(List<Part> parts);
    }

    public interface GetSections {
        void getSectionsSuccess(List<Section> sections);
    }

    public interface GetSection {
        void getSectionSuccess(Section sections);
    }

    public interface GetNovels {
        void getNovels(List<Novel> novels);
    }

}
