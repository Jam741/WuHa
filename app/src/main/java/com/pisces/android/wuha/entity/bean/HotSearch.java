package com.pisces.android.wuha.entity.bean;

/**
 * Created by Chris Li on 2017/9/17.
 */

public class HotSearch {

    /**
     * Id : 11
     * keyword : 磨牙棒
     * Count : 17
     */

    private int Id;
    private String keyword;
    private int Count;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }
}
