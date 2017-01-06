package com.maxi.weixiao;

/**
 * Created by mingzhi.yuan on 10/26/16.
 */

public class Section {

    private int novelId = -1;
    private int partPosition = -1;
    private int sectionPosition = -1;
    private String content = null;
    private String title = null;

    public int getPartPosition() {
        return partPosition;
    }

    public void setPartPosition(int position) {
        this.partPosition = position;
    }

    public int getSectionPosition() {
        return sectionPosition;
    }

    public void setSectionPosition(int position) {
        this.sectionPosition = position;
    }

    public int getNovelId() {
        return novelId;
    }

    public void setNovelId(int novelId) {
        this.novelId = novelId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
