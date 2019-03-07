package com.ifkbhit.imagefinder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifkbhit.imagefinder.R;
import com.ifkbhit.imagefinder.network.holder.Result;
import com.ifkbhit.imagefinder.srecycler.view.ASRVAdapter;

import java.util.List;

public class MoviesAdapter extends ASRVAdapter<MovieVH, Result> {
    private final Context context;

    public MoviesAdapter(List<Result> data, Context context) {
        super(data);
        this.context = context;
    }

    @NonNull
    @Override
    public MovieVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_movies, viewGroup, false);
        return new MovieVH(view, new MovieVB(view));
    }
}
