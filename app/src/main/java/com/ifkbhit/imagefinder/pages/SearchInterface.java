package com.ifkbhit.imagefinder.pages;

import com.ifkbhit.imagefinder.network.holder.MovieResponse;

public interface SearchInterface {
    void showToast(String str);
    void displayResult(MovieResponse movieResponse);
    void displayError(String s);
    void showProgressBar();
    void hideProgressBar();
}
