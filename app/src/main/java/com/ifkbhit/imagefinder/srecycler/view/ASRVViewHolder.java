package com.ifkbhit.imagefinder.srecycler.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

public abstract class ASRVViewHolder<T> extends RecyclerView.ViewHolder {


    protected final int STATE_IDLE = ItemTouchHelper.ACTION_STATE_IDLE;
    protected final int STATE_SWIPE_LEFT = ItemTouchHelper.LEFT;
    protected final int STATE_SWIPE_RIGHT = ItemTouchHelper.RIGHT;


    protected final ASRVViewBuilder<T> builder;
    private View itemView;



    /**
     * Constructor for removable ViewHolder by swipe
     */
    public ASRVViewHolder(@NonNull View itemView, ASRVViewBuilder<T> builder) {
        super(itemView);
        this.itemView = itemView;
        this.builder = builder;
    }


    void setData(T data, int position) {
        builder.onBind(data, position);
    }



    private View getItemView() {
        return itemView;
    }

    int getSwipeDir() {
        return getCurrentSwipeDir();
    }


    public int getCurrentSwipeDir() {
        return STATE_SWIPE_LEFT;
    }


}
