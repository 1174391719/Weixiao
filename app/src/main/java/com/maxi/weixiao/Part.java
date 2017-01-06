package com.maxi.weixiao;

import java.util.List;

/**
 * Created by mingzhi.yuan on 10/26/16.
 */

public class Part {
    private String title = null;
    private int partPosition = -1;
    private List<Section> sections = null;
    private int novelId = -1;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPartPosition() {
        return partPosition;
    }

    public void setPartPosition(int partPosition) {
        this.partPosition = partPosition;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> mSection) {
        this.sections = mSection;
    }

    public int getNovelId() {
        return novelId;
    }

    public void setNovelId(int novelId) {
        this.novelId = novelId;
    }
}
