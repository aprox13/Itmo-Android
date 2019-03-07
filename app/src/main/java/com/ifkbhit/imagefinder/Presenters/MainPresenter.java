package com.ifkbhit.imagefinder.Presenters;

import android.util.Log;

import com.ifkbhit.imagefinder.network.RetrofitClient;
import com.ifkbhit.imagefinder.network.holder.MovieResponse;
import com.ifkbhit.imagefinder.pages.MainInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements IMainPresenter {


    MainInterface mainInterface;


    private MovieResponse response = null;

    DisposableObserver<MovieResponse> observer = null;

    public MainPresenter(MainInterface mainInterface) {
        this.mainInterface = mainInterface;
        //available = (mainInterface != null);
    }

    @Override
    public void getMovies() {
        if (response != null) {
            if (isAvailable()) {
                mainInterface.displayMovies(response);
                response = null;
            }
        } else {
            if (observer != null) {
                observer.dispose();
            }
            observer = getObservable().subscribeWith(getObserver());
        }
    }


    @Override
    public void setActivity(MainInterface mainInterface) {
        this.mainInterface = mainInterface;
    }

    public Observable<MovieResponse> getObservable() {
        return RetrofitClient
                .getInstance()
                .getMovieApi()
                .getMovies("004cbaf19212094e32aa9ef6f6577f22")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<MovieResponse> getObserver() {
        return new DisposableObserver<MovieResponse>() {

            @Override
            public void onNext(@NonNull MovieResponse movieResponse) {
                Log.d(getClass().getSimpleName(), "OnNext" + movieResponse.getTotalResults());
                if (!isAvailable()) {
                    response = movieResponse;
                } else {
                    mainInterface.displayMovies(movieResponse);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(getClass().getSimpleName(), "Error" + e);
                e.printStackTrace();
                mainInterface.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.d(getClass().getSimpleName(), "Completed");
                mainInterface.hideProgressBar();
            }
        };
    }


    boolean isAvailable() {
        return mainInterface != null;
    }

}
