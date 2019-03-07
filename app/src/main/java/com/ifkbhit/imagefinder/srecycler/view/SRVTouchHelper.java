package com.ifkbhit.imagefinder.srecycler.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

public class SRVTouchHelper extends ItemTouchHelper.SimpleCallback {


    private Paint mClearPaint;
    private ColorDrawable mBackground;
    private int backgroundColor;
    private SRVTouchListener listener;

    private SRVOnDataChangedListener onDataChangedListener = new SRVOnDataChangedListener() {
        @Override
        public void onItemRemoved() {

        }

        @Override
        public void onItemRestored() {

        }
    };


    void setOnDataChangeListener(SRVOnDataChangedListener listener) {
        this.onDataChangedListener = listener;
    }

    SRVTouchHelper(Context context, SRVTouchListener listener) {
        super(0, ItemTouchHelper.LEFT);
        this.listener = listener;
        mBackground = new ColorDrawable();
        backgroundColor = (ContextCompat.getColor(context, android.R.color.transparent));
        mClearPaint = new Paint();
        mClearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        View itemView = viewHolder.itemView;

        boolean isCancelled = dX == 0 && !isCurrentlyActive;

        if (isCancelled) {
            clearCanvas(c, itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, false);
            return;
        }

        mBackground.setColor(backgroundColor);
        mBackground.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
        mBackground.draw(c);
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }


    private void clearCanvas(Canvas c, Float left, Float top, Float right, Float bottom) {
        c.drawRect(left, top, right, bottom, mClearPaint);

    }

    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return 0.7f;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView,
                                @NonNull RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof ASRVViewHolder) {
            return makeMovementFlags(0, getSwipeDirs(recyclerView, viewHolder));
        } else {
            return makeMovementFlags(0, 0);
        }
    }


    @Override
    public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof ASRVViewHolder) {
            return ((ASRVViewHolder) viewHolder).getSwipeDir();
        }

        return super.getSwipeDirs(recyclerView, viewHolder);
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition(), onDataChangedListener);
    }
}
