package com.ifkbhit.imagefinder.Presenters;

import android.annotation.SuppressLint;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.ifkbhit.imagefinder.network.RetrofitClient;
import com.ifkbhit.imagefinder.network.holder.MovieResponse;
import com.ifkbhit.imagefinder.pages.SearchInterface;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class SearchPresenter implements ISearchPresenter {

    SearchInterface searchViewInterface;

    public SearchPresenter(SearchInterface searchViewInterface) {
        this.searchViewInterface = searchViewInterface;
    }


    @SuppressLint("CheckResult")
    @Override
    public void getResultsBasedOnQuery(SearchView searchView) {

        getObservableSearchQuery(searchView)
                .filter(s -> !s.equals(""))
                .debounce(2, TimeUnit.SECONDS)
                .distinctUntilChanged()
                .switchMap((Function<String, ObservableSource<MovieResponse>>) s -> RetrofitClient.getInstance().getMovieApi()
                        .getMoviesBasedOnQuery("004cbaf19212094e32aa9ef6f6577f22", s))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver());


    }

    private Observable<String> getObservableSearchQuery(SearchView searchView) {

        final PublishSubject<String> publishSubject = PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                publishSubject.onNext(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                publishSubject.onNext(newText);
                return true;
            }
        });

        return publishSubject;
    }

    public DisposableObserver<MovieResponse> getObserver() {
        return new DisposableObserver<MovieResponse>() {

            @Override
            public void onNext(@NonNull MovieResponse MovieResponse) {
                Log.d(getClass().getSimpleName(), "OnNext" + MovieResponse.getTotalResults());
                searchViewInterface.displayResult(MovieResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(getClass().getSimpleName(), "Error" + e);
                e.printStackTrace();
                searchViewInterface.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.d(getClass().getSimpleName(), "Completed");
            }
        };
    }
}
