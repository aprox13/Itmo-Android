package com.ifkbhit.imagefinder.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.ifkbhit.imagefinder.network.holder.Result;
import com.ifkbhit.imagefinder.srecycler.view.ASRVViewBuilder;
import com.ifkbhit.imagefinder.srecycler.view.ASRVViewHolder;

public class MovieVH extends ASRVViewHolder<Result> {
    public MovieVH(@NonNull View itemView, ASRVViewBuilder<Result> builder) {
        super(itemView, builder);
    }

    @Override
    public int getCurrentSwipeDir() {
        return STATE_IDLE;
    }
}
