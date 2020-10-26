package com.example.ureportdemo;

import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.NumbericRenderData;

/**
 * @author : guoweifeng
 * @date : 2020/10/26
 */
public class MyDataModel {
        private String header;
        private String name;
        private String word;
        private String time;
        private String what;
        private MiniTableRenderData compare;
        private NumbericRenderData feature;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public MiniTableRenderData getCompare() {
        return compare;
    }

    public void setCompare(MiniTableRenderData compare) {
        this.compare = compare;
    }

    public NumbericRenderData getFeature() {
        return feature;
    }

    public void setFeature(NumbericRenderData feature) {
        this.feature = feature;
    }
}
