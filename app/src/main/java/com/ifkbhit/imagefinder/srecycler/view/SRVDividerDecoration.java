package com.ifkbhit.imagefinder.srecycler.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

public class SRVDividerDecoration extends RecyclerView.ItemDecoration{

    private int dividerInDp;
    private Context context;

    public SRVDividerDecoration(int dividerInDp, Context context){
        this.dividerInDp = dividerInDp;
        this.context = context;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, convertDpToPixel(dividerInDp, context));
    }

    private int convertDpToPixel(int dp, Context context){
        return (int) (dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
