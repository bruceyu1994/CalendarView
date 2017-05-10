package com.bruceyu.library;

import java.io.Serializable;

/**
 * Created by BruceYu on 2017/4/7 20:22.
 * Email: 13738725570@163.com
 * Description:
 */

public class SelectedDays<K> implements Serializable {
//    private static final long serialVersionUID = 3942549765282708376L;
    private K first;
    private K last;

    public SelectedDays() {

    }

    public SelectedDays(K first, K last) {
        this.first = first;
        this.last = last;
    }

    public K getFirst() {
        return first;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public K getLast() {
        return last;
    }

    public void setLast(K last) {
        this.last = last;
    }
}