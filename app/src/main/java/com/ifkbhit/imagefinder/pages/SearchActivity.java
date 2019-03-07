package com.ifkbhit.imagefinder.pages;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ifkbhit.imagefinder.Presenters.SearchPresenter;
import com.ifkbhit.imagefinder.R;
import com.ifkbhit.imagefinder.adapter.MoviesAdapter;
import com.ifkbhit.imagefinder.network.holder.MovieResponse;
import com.ifkbhit.imagefinder.srecycler.view.SuperRecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_search)
public class SearchActivity extends AppCompatActivity implements SearchInterface {


    private SearchView searchView;
    private SearchPresenter searchPresenter;
    private MoviesAdapter adapter;

    @ViewById
    Toolbar toolbar;

    @ViewById
    SuperRecyclerView recyclerView;

    @ViewById
    ProgressBar progressBar;


    @AfterViews
    void init() {
        setSupportActionBar(toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchPresenter = new SearchPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Enter Movie name..");

        searchPresenter.getResultsBasedOnQuery(searchView);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(SearchActivity.this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayResult(MovieResponse movieResponse) {
        adapter = new MoviesAdapter(movieResponse.getResults(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayError(String s) {
        showToast(s);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
