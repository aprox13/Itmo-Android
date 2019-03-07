package com.ifkbhit.imagefinder.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifkbhit.imagefinder.R;
import com.ifkbhit.imagefinder.network.holder.Result;
import com.ifkbhit.imagefinder.srecycler.view.ASRVViewBuilder;
import com.squareup.picasso.Picasso;

public class MovieVB extends ASRVViewBuilder<Result> {
    TextView title, overview, date;
    ImageView img;

    protected MovieVB(View rootView) {
        super(rootView);
    }

    @Override
    protected void onCreate() {
        title = findViewById(R.id.title);
        overview = findViewById(R.id.overview);
        date = findViewById(R.id.date);
        img = findViewById(R.id.img);
    }

    @Override
    public void onBind(Result result, int position) {
        title.setText(result.getTitle());
        overview.setText(result.getOverview());
        date.setText(result.getReleaseDate());
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + result.getPosterPath()).into(img);
    }
}
