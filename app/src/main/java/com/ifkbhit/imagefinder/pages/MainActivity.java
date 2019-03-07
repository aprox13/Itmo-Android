package com.ifkbhit.imagefinder.pages;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ifkbhit.imagefinder.Presenters.MainPresenter;
import com.ifkbhit.imagefinder.R;
import com.ifkbhit.imagefinder.adapter.MoviesAdapter;
import com.ifkbhit.imagefinder.network.holder.MovieResponse;
import com.ifkbhit.imagefinder.srecycler.view.SuperRecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;


@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu)
public class MainActivity extends AppCompatActivity implements MainInterface {


    @ViewById
    SuperRecyclerView recycler;

    @ViewById
    ProgressBar progressBar;

    @ViewById
    Toolbar toolbar;

    @NonConfigurationInstance
    MainPresenter presenter;
    MoviesAdapter adapter;

    @AfterViews
    void init() {

        setSupportActionBar(toolbar);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        // setup MVP
        if(presenter == null) {
            presenter = new MainPresenter(this);
        }
        presenter.setActivity(this);
        // setup movies
        presenter.getMovies();
    }


    @Override
    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayMovies(MovieResponse movieResponse) {
        if (movieResponse == null) {
            Log.d(this.getClass().getSimpleName(), "Movie response is null");
            return;
        }
        adapter = new MoviesAdapter(movieResponse.getResults(), this);
        recycler.setAdapter(adapter);
    }

    @Override
    public void displayError(String s) {
        showToast(s);
    }

    @OptionsItem(R.id.search)
    void search() {
        SearchActivity_.intent(this).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.setActivity(null);
    }
}
