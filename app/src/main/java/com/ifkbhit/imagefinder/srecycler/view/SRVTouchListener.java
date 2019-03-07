package com.ifkbhit.imagefinder.srecycler.view;

import android.support.v7.widget.RecyclerView;

public interface SRVTouchListener {
    void onSwiped(RecyclerView.ViewHolder holder, int direction, int position, SRVOnDataChangedListener listener);
}
