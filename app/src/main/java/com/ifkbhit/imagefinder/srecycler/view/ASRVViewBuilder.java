package com.ifkbhit.imagefinder.srecycler.view;

import android.support.annotation.IdRes;
import android.view.View;

public abstract class ASRVViewBuilder<DataToView> {

    private View rootView;
    private View background;
    protected View foreground;

    protected ASRVViewBuilder(View rootView) {
        this.rootView = rootView;
        onCreate();
    }

    protected View getRootView() {
        return rootView;
    }

    public void onBind(DataToView data) {
        onBind(data, -1);
    }

    public void setBackground(View background) {
        this.background = background;
    }

    public void setForeground(View foreground) {
        this.foreground = foreground;
    }

    protected abstract void onCreate();

    public abstract void onBind(DataToView data, int position);

    protected <T extends View> T findViewById(@IdRes int id) {
        return getRootView().findViewById(id);
    }


}
