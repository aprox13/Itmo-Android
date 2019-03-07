package com.ifkbhit.imagefinder.pages;

import com.ifkbhit.imagefinder.network.holder.MovieResponse;

public interface MainInterface {
    void showToast(String s);

    void hideProgressBar();

    void displayMovies(MovieResponse movieResponse);

    void displayError(String s);
}
