package com.materialdesigndemo.android_materialdesign_view.interfaces;

import android.support.v7.widget.RecyclerView;

/**
 * Created by m on 2016/11/4.
 */
public interface OnScrollCallback {

    void onStateChanged(RecyclerView recyclerView, int state);

    void onScrollUp(RecyclerView recyclerView, int dy);

    void onScrollToBottom();

    void onScrollDown(RecyclerView recyclerView, int dy);

    void onScrollToTop();
}
