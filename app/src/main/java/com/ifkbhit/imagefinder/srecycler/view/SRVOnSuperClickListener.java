package com.ifkbhit.imagefinder.srecycler.view;

public interface SRVOnSuperClickListener {
    void onItemDoubleClick(int adapterPosition);

    void onItemClick(int adapterPosition);

    default void onItemLongClick(int adapterPosition){}
}