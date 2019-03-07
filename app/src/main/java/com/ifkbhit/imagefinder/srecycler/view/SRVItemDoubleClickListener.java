package com.ifkbhit.imagefinder.srecycler.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


/*
* https://gist.github.com/RanyAlbegWein/271258193d466e8cbabd
* */
public class SRVItemDoubleClickListener extends RecyclerView.SimpleOnItemTouchListener {

    private OnItemDoubleClickEventListener mOnItemClickListener;
    private GestureDetector mGestureDetector;
    private View mChildView;
    private int mChildViewAdapterPosition;

    public SRVItemDoubleClickListener(OnItemDoubleClickEventListener listener, Context context) {
        mGestureDetector = new GestureDetector(context, new GestureDelegate());
        mOnItemClickListener = listener;
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        mChildView = recyclerView.findChildViewUnder(x, y);

        if (mChildView != null) {
            mChildViewAdapterPosition = recyclerView.getChildAdapterPosition(mChildView);
            mGestureDetector.onTouchEvent(motionEvent);
        }
        return false;
    }

    public interface OnItemDoubleClickEventListener {
        void onItemDoubleClick(int adapterPosition);
    }

    private class GestureDelegate extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (mOnItemClickListener != null) {
                if (mChildView != null) {
                    mOnItemClickListener.onItemDoubleClick(mChildViewAdapterPosition);
                    return true;
                }
            }
            return false;
        }
    }
}
