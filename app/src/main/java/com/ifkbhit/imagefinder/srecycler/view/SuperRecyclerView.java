package com.ifkbhit.imagefinder.srecycler.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;

public class SuperRecyclerView extends RecyclerView {

    private SRVTouchHelper helper;
    private SRVOnDataChangedListener listener = new SRVOnDataChangedListener() {
        @Override
        public void onItemRemoved() {

        }

        @Override
        public void onItemRestored() {

        }
    };


    public SuperRecyclerView(@NonNull Context context) {
        super(context);
    }

    public SuperRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SuperRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setItemAnimator(new DefaultItemAnimator());
    }


    public void addOnSuperClickListener(SRVOnSuperClickListener listener) {
        addOnItemTouchListener(new SRVSuperClickListener(listener, getContext()));
    }

    public void setDefault(ASRVAdapter<?, ?> adapter, LayoutManager manager) {
        setLayoutManager(manager);
        setAdapter(adapter);
        setItemAnimator(new DefaultItemAnimator());
    }


    public void setTouchHelperListener(SRVTouchListener listener) {
        helper = (new SRVTouchHelper(getContext(), listener));
        helper.setOnDataChangeListener(this.listener);
        new ItemTouchHelper(helper).attachToRecyclerView(this);
    }

    public void setOnDataChangeListener(SRVOnDataChangedListener listener) {
        this.listener = listener;
        if (helper != null) {
            helper.setOnDataChangeListener(listener);
        }
    }

}
